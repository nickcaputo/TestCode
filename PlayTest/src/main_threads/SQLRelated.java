package main_threads;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;

import utilities.Constants;
import utilities.Write;

public class SQLRelated {

	/**
	 * Allows you to continuously run SQL Queries on a database and see the
	 * results in the console!
	 */
	public static void runSQLQueriesOnDatabase() {
		Connection root = getConnectionToPPro("C:/Rainmaker/ProspectorPro.mdb", "MiguelAngel");
		Scanner console = new Scanner(System.in);
		boolean running = true;

		while (running) {
//			Write.writeLine("What is the activity type?");
//			String activityType = console.nextLine();
//			Write.writeLine("What are the notes?");
//			String notes = console.nextLine();
//			Write.writeLine("What is the id?");
//			String id = console.nextLine();
//			updateActivityNotes_noQueries(root, notes, activityType, id);

			ResultSet contacts = queryDatabase(console, root);

			boolean contactsExist = contacts != null;
			Write.writeLine(contactsExist ? "Reading result set" : "No result set returned");

			if (contactsExist) {
				try {
					readResultSet(contacts);
				} catch (SQLException err) {
					Write.writeLine("SQLException: " + err.getMessage());
				}
			}
		}
		console.close();
	}

	public static int updateActivityNotes_noQueries(Connection root, String note, String activityType, String id) {

		String statement = null;

		switch (activityType) {
		case "Call":
			statement = "UPDATE Calls SET Notes=? WHERE CallId=?";
			break;
		case "Correspondence":
			statement = "UPDATE Correspondence SET Memo=? WHERE CorrId=?";
			break;
		case "Task":
			statement = "UPDATE Tasks SET Notes=? WHERE TaskId=?";
			break;
		}

		if (statement != null) {
			try {
				PreparedStatement run = root.prepareStatement(statement);

				run.setString(1, note);
				run.setString(2, id);

				return run.executeUpdate();
			} catch (SQLException err) {
				err.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}

	}

	/**
	 * Intended to get a connection to a Prospector Pro Access database, but can
	 * connect to any Access database.
	 * 
	 * @param path,
	 *            the file path to the db
	 * @param pass,
	 *            the password, or an empty string if not password protected
	 * @return a connection object to the database
	 */
	private static Connection getConnectionToPPro(String path, String pass) {
		Write.writeLine("Now loading database into memory");
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			long current = System.currentTimeMillis();
			Connection root = DriverManager.getConnection("jdbc:ucanaccess://" + path /* + ";memory:false" */, "",
					pass);
			long future = System.currentTimeMillis();

			Write.writeLine("Loading the database took " + (future - current) + " ms.");

			return root;
		} catch (SQLException err) {
			Write.writeLine("SQLException: " + err.getMessage());
			return null;
		} catch (ClassNotFoundException err) {
			Write.writeLine("Class not found.");
			return null;
		}
	}

	/**
	 * Creates a PreparedStatement and executes it on the database, and records
	 * the time in ms it took for the query to execute.
	 * 
	 * @param console,
	 *            connected to a System.in console or text file that contains
	 *            SQL Queries, it allows you to enter a query.
	 * @param root,
	 *            the connection to the database
	 * @return the resultSet from the database, or null if query had an error
	 */
	private static ResultSet queryDatabase(Scanner console, Connection root) {
		Write.writeLine("Enter an SQL Query: ");
		String getContactsSQL = console.nextLine();
		String firstCommand = getContactsSQL.substring(0, 6);

		try {
			if (firstCommand.equals("SELECT")) {
				PreparedStatement statement = root.prepareStatement(getContactsSQL);
				// statement.setInt(1, amount);

				Write.writeLine("Running query.");
				long current = System.currentTimeMillis();
				ResultSet set = statement.executeQuery();
				long future = System.currentTimeMillis();

				Write.writeLine("Query took " + (future - current) + " ms.");

				return set;
			} else {
				PreparedStatement statement = root.prepareStatement(getContactsSQL);

				Write.writeLine("Running query.");
				long current = System.currentTimeMillis();
				statement.executeUpdate();
				long future = System.currentTimeMillis();

				Write.writeLine("Query took " + (future - current) + " ms.");

				return null;
			}
		} catch (SQLException err) {
			Write.writeLine("SQLException: " + err.getMessage());
			return null;
		} finally {
			Write.writeLine("Repeating this method");
		}
	}

	/**
	 * Reads the metadata of a resultset, and prints out all columns and rows as
	 * text in the console. This is called immediately after the queryDatabase
	 * method.
	 * 
	 * @param set,
	 *            the resultSet to print
	 * @throws SQLException,
	 *             if there was a connection error
	 */
	private static void readResultSet(ResultSet set) throws SQLException {
		ResultSetMetaData md = set.getMetaData();

		while (set.next()) {
			for (int i = 1; i <= md.getColumnCount(); i++) {
				System.out.print(" " + set.getObject(i));
			}
			System.out.println();
		}
	}

	/**
	 * Typically used when querying the Prospector Pro contact table, it takes a
	 * single contact and serializes its information into a string that can be
	 * parsed by another machine into a PProContact object.
	 * 
	 * @param contactsToShow,
	 *            how many contacts to retrieve from this resultSet
	 * @param commandSelection,
	 *            whether we are showing full information, a small amount, or
	 *            something in between (this is a set code understood by both
	 *            the server and the client)
	 * @param notesToSend,
	 *            in an earlier version, the notes were sometimes queried before
	 *            the contact was serialized. If they are present, add them to
	 *            the result.
	 * @param quickSearchingMoreContacts,
	 *            if set to true, we should begin at another contact than the
	 *            first one @return, the serialized string to send
	 */
	private static String serializeContact(ResultSet contactsToShow, String commandSelection, String notesToSend,
			boolean quickSearchingMoreContacts) {

		/*
		 * the amount of items to get at one time should we be selecting the
		 * "Get More Contacts" feature
		 */
		int limit = 100;

		try {
			StringBuilder contactItemsBuilder = new StringBuilder();
			int contactNumber = 0;
			char SEPARATOR = Constants.SEPARATOR;

			while (contactsToShow.next()) {

				if ((quickSearchingMoreContacts && contactNumber > limit)
						|| (!quickSearchingMoreContacts && contactNumber != 0)) {
					contactItemsBuilder.append(Constants.ITEM_SEP);
				}

				if (!quickSearchingMoreContacts || (quickSearchingMoreContacts && contactNumber >= limit)) {

					contactItemsBuilder.append(SEPARATOR).append("Contact ").append(contactNumber).append(" ")
							.append(SEPARATOR).append("custid:" + contactsToShow.getString("CustId")).append(SEPARATOR)
							.append("fName:" + contactsToShow.getString("Fname")).append(SEPARATOR)
							.append("lName:" + contactsToShow.getString("Lname")).append(SEPARATOR)
							.append("email:" + contactsToShow.getString("Email")).append(SEPARATOR)
							.append("cellphone:" + contactsToShow.getString("OtherPhone")).append(SEPARATOR)
							.append("lookingfor:" + contactsToShow.getString("Style")).append(SEPARATOR)
							.append("timeframe:" + contactsToShow.getString("Title")).append(SEPARATOR)
							.append("mi:" + contactsToShow.getString("Middle")).append(SEPARATOR)
							.append("class:" + contactsToShow.getString("Class")).append(SEPARATOR)
							.append("score:" + contactsToShow.getString("Score")).append(SEPARATOR)
							.append("company:" + contactsToShow.getString("Company")).append(SEPARATOR).append("notes:")
							.append(notesToSend);

					if (!(commandSelection.equals("qs") || commandSelection.equals("qsMore")
							|| commandSelection.equals("search"))) {
						contactItemsBuilder.append(SEPARATOR).append("address:")
								.append(contactsToShow.getString("Address") + SEPARATOR).append("city:")
								.append(contactsToShow.getString("City")).append(SEPARATOR)
								.append("state:" + contactsToShow.getString("State")).append(SEPARATOR)
								.append("zipCode:" + contactsToShow.getString("ZipCode")).append(SEPARATOR)
								.append("budget:" + contactsToShow.getString("Country")).append(SEPARATOR)
								.append("niche:" + contactsToShow.getString("Industry")).append(SEPARATOR)
								.append("homephone:" + contactsToShow.getString("HomPhone")).append(SEPARATOR)
								.append("workphone:" + contactsToShow.getString("WorkPhone")).append(SEPARATOR);
					}

				}

				contactNumber++;
			}

			String contactItems = contactItemsBuilder.toString();
			return contactItems;
		} catch (SQLException err) {
			err.printStackTrace();
			return null;
		}
	}

	/**
	 * Establishes a connection to a MySQL database.
	 */
	public static void setConnectionToMySQLDb() {
		Connection conn = null;
		String dbUrl = "jdbc:mysql://localhost:3306/";
		String name = "heroes";
		String driver = "com.mysql.jdbc.Driver";
		String username = "root";
		String pass = "abstracted";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dbUrl + name, username, pass);
			System.out.println("Connected to the database!");

			String statement = "SELECT * FROM profiles";
			PreparedStatement selector = conn.prepareStatement(statement);
			ResultSet results = selector.executeQuery();

			while (results.next()) {
				String personName = results.getString("firstName") + ' ' + results.getString("lastName");
				System.out.println(personName);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

}
