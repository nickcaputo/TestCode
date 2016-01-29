package main_threads;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import utilities.StringCountComparator;

public class PlayTest {

	public static void main(String[] args) {

		long currentTime = System.currentTimeMillis();

		// addToQueue();

		// getMaxProfit();

		System.out.println(compress("abcdefggghhhiiijjjkkkklllmmmnnnoopppqqqrrrssstttuuuvvvwwwxxxyyyzzzzz"));

		System.out.println(replaceSpaces("Mr Haim Style    ", 13)); // 0-1 ms

		// log3(81); // takes 3-7 ms

		// setConnectionToDatabase();

		// executeCalc30Seconds();

		// hasher();

		// play();

		long futureTime = System.currentTimeMillis();

		System.out.println("This took " + (futureTime - currentTime) + " ms.");

	}

	public static String replaceSpaces(String input, int inputLength) {
		char[] array = new char[input.length()];
		int arrCount = 0;

		for (int i = 0; i < inputLength; i++) {
			char point = input.charAt(i);

			if (point != ' ') {
				array[arrCount] = point;
				arrCount++;
			} else {
				array[arrCount] = '%';
				array[arrCount + 1] = '2';
				array[arrCount + 2] = '0';
				arrCount = arrCount + 3;
			}
		}

		String string = new String(array);

		return string;
	}

	public static String compress(String input) {
		int inputLength = input.length();
		if (inputLength <= 2) {
			// System.out.println("Returning due to length.");
			return input;
		}

		StringBuilder builder = new StringBuilder();
		char focus = input.charAt(0); // set to a
		long count = 1;

		for (int i = 1; i < inputLength; i++) {
			char point = input.charAt(i);
			if (point == focus) {
				// System.out.println("incrementing count");
				count++;
			} else {
				// System.out.println("found new character in string");
				builder.append("" + focus + count);
				focus = point;
				count = 1;
			}
		}

		// after reaching last point in the array
		builder.append("" + focus + count);

		if (builder.length() >= inputLength) {
			return input;
		} else {
			String compressedString = builder.toString();
			return compressedString;
		}
	}

	public static void hasher() {
		HashMap<String, Integer> hasher = new HashMap<>();

		Scanner console = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			String artist = console.nextLine();

			Integer numberOfSongs = hasher.get(artist);
			if (!hasher.containsKey(artist)) {
				numberOfSongs = 1;
				hasher.put(artist, numberOfSongs);
			} else {
				numberOfSongs += 1;
				hasher.remove(artist);
				hasher.put(artist, numberOfSongs);
			}

			System.out.println(numberOfSongs);
		}

		List<Entry<String, Integer>> valuesByCount = new ArrayList<Entry<String, Integer>>(hasher.entrySet());

		Collections.sort(valuesByCount, new StringCountComparator());

		for (Entry<String, Integer> entry : valuesByCount) {
			System.out.println(entry.toString());
		}

		console.close();
	}

	public static void setConnectionToDatabase() {
		Connection conn = null;
		String dbUrl = "jdbc:mysql://localhost:3306/";
		String name = "heroes";
		String driver = "com.mysql.jdbc.Driver";
		String username = "root";
		String pass = "Printer1!@";

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

	public static void getMaxProfit() {

		int[] stocks = new int[] { 9, 18, 7, 9, 20, 10, 3, 9, 29 };

		if (stocks.length < 2) {
			throw new IllegalArgumentException("You have to have at least two prices to sell at.");
		}

		int maxProfit = stocks[1] - stocks[0];
		int minPrice = stocks[0];

		for (int i = 1; i < stocks.length; i++) {

			int profit = stocks[i] - minPrice;

			if (stocks[i] < minPrice) {
				minPrice = stocks[i];
			}

			if (maxProfit < profit) {
				maxProfit = profit;
			}
		}

		System.out.println(maxProfit);

	}

	public static void addToQueue() {
		TreeSet<String> queues = new TreeSet<>();

		queues.add("S");
		queues.add("T");
		queues.add("A");
		queues.add("R");
		queues.add("T");
		queues.add("E");
		queues.add("D");
		queues.add("S");

		System.out.println(queues.higher("R"));
		SortedSet<String> headSet = queues.headSet("R");

		System.out.println("\nHeadSet");
		for (String element : headSet) {
			System.out.println(element);
		}

		System.out.println("\n" + queues.size());

		int size = queues.size();
		Iterator<String> iterator = queues.iterator();
		for (int i = 0; i < size; i++) {
			if (iterator.hasNext() == true) {
				System.out.println(iterator.next());
			} else {
				System.out.println("null");
			}
		}
	}

	public static void executeCalc30Seconds() {
		while (true) {

			try {
				Runtime.getRuntime().exec("calc");
				Thread.sleep(10000);
			} catch (InterruptedException | IOException err) {
				err.printStackTrace();
			}
		}
	}

	public static void play(int[] coins) {
		double numberPower = Math.sqrt(8);
		System.out.println(numberPower);
		System.out.println(Math.floor(numberPower));
		System.out.println(numberPower == Math.floor(numberPower));

	}

	public static void log3(double number) {
		double log = (Math.log(number) / Math.log(3));
		System.out.println(log);
	}

}