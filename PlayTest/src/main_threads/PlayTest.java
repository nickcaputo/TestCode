package main_threads;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.management.ManagementFactory;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import materials.Node;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import utilities.Constants;
import utilities.StringCountComparator;
import utilities.Write;

public class PlayTest {

	public static void main(String[] args) {
		long currentTime = System.currentTimeMillis();

		runThisStuff();

		Rectangle rect = new Rectangle(1, 0, 13, 9);
		Write.quickWrite(rect.x);

		long futureTime = System.currentTimeMillis();
		Write.quickWrite("This took " + (futureTime - currentTime) + " ms.");
	}

	private static void runThisStuff() {
		// addToQueue();

		// getMaxProfit(new int[] { 9, 18, 7, 9, 20, 10, 3, 9, 29 });

		Write.quickWrite(compress("abcdefggghhhiiijjjkkkklllmmmnnnoopppqqqrrrssstttuuuvvvwwwxxxyyyzzzzz"));

		// replaceSpaces("Mr Haim Style ", 13); // 0-1 ms
		//
		// checkSteps(1999);
		//
		// reverseString("Ripley");

		// log3(81); // takes 3-7 ms

		// setConnectionToDatabase();

		// executeCalc30Seconds();

		// hasher();

		// s.examineDoubles();

		int[][] array = new int[][] { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 1 }, { 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 0 } };

		setZeroes(array);

		try {
			readFromConsole();
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		}

		// String paint = "paintcan";
		// String can = "intcanpa";
		// Write.quickWrite(isStringRotation(paint, can));
	}

	/**
	 * Takes a string object as a parameter, and returns a reversed. Example:
	 * Input: "Ripley", Output: "yelpiR"
	 * 
	 * @param string,
	 *            string to reverse
	 * @return new String of reversed input
	 */
	public static String reverseString(String string) {
		char[] array = string.toCharArray();
		char[] reversed = new char[array.length];

		int count = 0;
		for (int i = array.length - 1; i >= 0; i--) {
			reversed[count] = array[i];
			count++;
		}

		return new String(reversed);
	}

	/**
	 * Takes a string as a parameter, and replaces the spaces in it with '%20'
	 * symbols
	 * 
	 * @param input,
	 *            the string to replace
	 * @param inputLength,
	 *            the length of the substring to consider when replacing
	 *            spaces @return, the string with all spaces replaced
	 */
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

	private static float getMean() {
		int sum = 99999;
		int size = 5;
		float mean = sum / size;
		Write.quickWrite("Mean is " + mean);
		return mean;
	}

	/**
	 * Test method which adds items from a collection to a HashMap to get an
	 * idea of how many duplicates there are in the collection.
	 */
	private static void hashMap() {
		Map<Integer[], Integer> counter = new HashMap<>();
		List<Integer[]> cellHistory = new ArrayList<>();

		for (Integer[] item : cellHistory) {
			Integer number = counter.get(item);
			if (number == null) {
				counter.put(item, 1);
			} else {
				counter.remove(item);
				number++;

				counter.put(item, number);
			}

			if (cellHistory.size() == counter.size()) {
				Write.writeLine("Already most efficient path!");
			} else {
				// Write.writeLine("Can be more efficient");
				// trim the ArrayList
			}
		}
	}

	/**
	 * Basic recursion method which checks how many steps a person would need to
	 * take to get to the top of a staircase, provided they skip 3 steps each
	 * time.
	 * 
	 * @param sizeOfStaircase,
	 *            the amount of steps in the staircase @return, how many steps
	 *            to take to get to the top
	 */
	private static int checkSteps(int sizeOfStaircase) {

		if (sizeOfStaircase <= 3) {
			return 1;
		} else {
			return 1 + checkSteps(sizeOfStaircase - 3);
		}

	}

	/**
	 * Uses a scanner to read from a file or system.in, printing the results.
	 * @throws FileNotFoundException
	 */
	private static void readFromConsole() throws FileNotFoundException {
		Scanner console = new Scanner(System.in);

		System.out.println(console.nextLine());
		
		console.close();
	}

	/**
	 * Opens a file and reads input from it.
	 */
	private static void inputPrint() {
		File file = new File("C:/TWL98.txt");

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));

			for (int i = 0; i < 168001; i++) {
				System.out.println(reader.readLine());
			}

			reader.close();
		} catch (IOException err) {
			err.printStackTrace();
		}

	}

	/**
	 * Compresses a string by removing repeating characters and replacing them
	 * with the character followed by the amount of times it is repeated.
	 * Example: Input: "aaaaaaabbbbbcccxxxyyyzzz", Output: "a7b5c3x3y3z3"
	 * 
	 * @param input,
	 *            String to compress @return, compressed string, or the same
	 *            string if compression would not make it shorter
	 */
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

	/**
	 * Given a 2D array of M x N dimensions, this sets the entire row and column
	 * to 0 if a 0 is
	 * 
	 * @param mxnArray
	 */
	public static void setZeroes(int[][] mxnArray) {
		int columns = mxnArray.length;
		int rows = mxnArray[0].length;
		List<Integer> colsToChange = new ArrayList<>();
		List<Integer> rowsToChange = new ArrayList<>();

		System.out.println("Before:\n");
		print2DArray(mxnArray);

		for (int i = 0; i < columns; i++) { // iterates through columns
			for (int j = 0; j < rows; j++) { // iterates through rows
				if (mxnArray[i][j] == 0) {
					colsToChange.add(i);
					rowsToChange.add(j);
				}
			}
		}

		// change columns to 0
		for (int i = 0; i < colsToChange.size(); i++) {
			for (int j = 0; j < rows; j++) {
				mxnArray[colsToChange.get(i)][j] = 0;
			}
		}

		// change rows to 0
		for (int i = 0; i < rowsToChange.size(); i++) {
			for (int j = 0; j < columns; j++) {
				mxnArray[j][rowsToChange.get(i)] = 0;
			}
		}

		System.out.println("\nAfter:\n");
		print2DArray(mxnArray);
	}

	/**
	 * Prints an evenly spaced 2D array.
	 * 
	 * @param print
	 */
	public static void print2DArray(int[][] print) {
		for (int i = 0; i < print.length; i++) {
			for (int j = 0; j < print[0].length; j++) {
				System.out.print(print[i][j]);
			}
			System.out.println();
		}
	}

	public static boolean isStringRotation(String original, String suspectedRotation) {
		StringBuilder builder = new StringBuilder(original);
		builder.append(original);
		return builder.toString().contains(suspectedRotation);
	}

	/**
	 * Searches a tree for a goal node in a depth first manner. This problem is
	 * recursive.
	 */
	public static Node traverseTree(Node node, int goal) {
		if (node.data == goal) {
			return node;
		} else if (node.left != null) {
			Node returned = traverseTree(node.left, goal);
			if (returned.data == goal) {
				return returned;
			}
		} else if (node.right != null) {
			Node returned = traverseTree(node.right, goal);
			if (returned.data == goal) {
				return returned;
			}
		}

		// called when tree is searched completely
		return null;
	}

	/**
	 * Used in a project of mine. This reads a file or dynamic input and adds
	 * the content to a HashMap, keeping track of duplicates and indicating how
	 * many duplicates are in the map.
	 */
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

	/**
	 * Establishes a connection to a MySQL database.
	 */
	public static void setConnectionToDatabase() {
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

	/**
	 * Given a set of stock values, finds the maximum profit we could make by
	 * selling them at any point.
	 * 
	 * @param stocks,
	 *            the list of stocks to check for the maximum profit
	 */
	public static void getMaxProfit(int[] stocks) {

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

	/**
	 * Test method adding items to a queue.
	 */
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

	/**
	 * Executes the calculator on a windows machine every 30 seconds.
	 */
	public static void executeCalc30Seconds() {
		while (true) {

			try {
				Runtime.getRuntime().exec("calc");
				Thread.sleep(30000);
			} catch (InterruptedException | IOException err) {
				err.printStackTrace();
			}
		}
	}

	/**
	 * A general use method, compares whether an int and a double.floor() are
	 * equal.
	 */
	public static void examineDoubles() {
		double numberPower = Math.sqrt(8);
		System.out.println(numberPower);
		System.out.println(Math.floor(numberPower));
		System.out.println(numberPower == Math.floor(numberPower));

	}

	/**
	 * Takes a number and returns that number in log base 3
	 * 
	 * @param number,
	 *            the number to log
	 * @return the number in log base 3
	 */
	public double log3(double number) {
		double log = (Math.log(number) / Math.log(3));
		System.out.println(log);
		return log;
	}

	/**
	 * Method testing the effects of bit manipulation.
	 * 
	 * @param bit,
	 *            the number to be represented in binary
	 * @param shift,
	 *            how much to shift this bit left/right
	 */
	private static void bitManipulate(long bit, int shift) {
		System.out.println("Input integer (binary): " + Long.toBinaryString(bit));
		System.out.println("Input shift (binary)  : " + Long.toBinaryString(shift) + System.lineSeparator());

		long shiftLeft = bit << shift;
		long shiftRight = bit >> shift;
		long inclusiveOr = bit | shift;
		long exclusiveOr = bit ^ shift;
		long and = bit & shift;

		System.out.println("Shifting by " + shift + " bits.");
		System.out.println("Shift left : " + shiftLeft);
		System.out.println("Shift right: " + shiftRight + System.lineSeparator());

		System.out.println("And (decimal): " + and);
		System.out.println("And (binary) : " + Long.toBinaryString(and) + System.lineSeparator());

		System.out.println("Inclusive or (decimal): " + inclusiveOr);
		System.out.println("Inclusive or (binary) : " + Long.toBinaryString(inclusiveOr));
		System.out.println("Exclusive or (decimal): " + exclusiveOr);
		System.out.println("Exclusive or (binary) : " + Long.toBinaryString(exclusiveOr));
	}

	/**
	 * Prints the label for the location in memory that this program is using.
	 */
	private static void getAndDisplayMemoryId() {
		String id = ManagementFactory.getRuntimeMXBean().getClassPath();
		System.out.println(id);
	}

	/**
	 * Tests an experimental regular expression for use in TCP/IP.
	 */
	private static void testExperimentalRegex() {
		final String SEPARATOR = "§¶§";
		System.out.println(SEPARATOR);

		String test = "This" + SEPARATOR + SEPARATOR + "is" + SEPARATOR + 'a' + SEPARATOR + "string";

		String revised = test.replaceAll(SEPARATOR, System.lineSeparator());

		System.out.println(revised);
	}

	/**
	 * Recieves an image over the network and converts the byte[] to files
	 */
	private static void imageProcessing() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException err) {
			err.printStackTrace();
		}

		short fileCounter = 1;

		try {
			/*
			 * create welcoming socket at port on client
			 */
			ServerSocket welcomeSocket = new ServerSocket(Constants.PORT);
			int stockNumber = 100002;

			File image = new File(stockNumber + "-" + fileCounter + ".jpg");
			int totalBytes = 0;
			List<Integer> locationsOfFiles = new ArrayList<>();

			if (image.exists()) {
				do {
					if (totalBytes < Integer.MAX_VALUE - (int) image.length()) {
						locationsOfFiles.add((int) image.length());
						System.out.println(image.getName() + " is " + image.length() + " bytes.");
						fileCounter++;
						totalBytes = totalBytes + (int) image.length();
						image = new File(stockNumber + "-" + fileCounter + ".jpg");
						// System.out.println("image exists " + image.exists());
					} else {
						break;
					}
				} while (image.exists());

				System.out.println(totalBytes);
				System.out.println(locationsOfFiles.size());

				byte[] files = convertFileToBytes(stockNumber, locationsOfFiles, totalBytes);
				sendData(files, locationsOfFiles, stockNumber, welcomeSocket);
			}

			welcomeSocket.close();
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	/**
	 * Converts a file to a byte array to send over the network.
	 * 
	 * @param stockNumber,
	 *            the label for the file
	 * @param locationsOfFiles,
	 *            the points in the array that denote new files
	 * @param totalBytes,
	 *            the total bytes in the array
	 * @return the byte array of all files combined
	 */
	private static byte[] convertFileToBytes(int stockNumber, List<Integer> locationsOfFiles, int totalBytes) {
		byte[] byter = new byte[totalBytes];
		int fileOffset = 0;

		for (int index = 0; index < locationsOfFiles.size(); index++) {
			File file = new File(stockNumber + "-" + (index + 1) + ".jpg");
			if (file.exists()) {
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					System.out.println("reading bytes starting at " + fileOffset + " for " + locationsOfFiles.get(index)
							+ " bytes");
					fileInputStream.read(byter, fileOffset, locationsOfFiles.get(index));

					fileOffset = fileOffset + locationsOfFiles.get(index);

					fileInputStream.close();
				} catch (FileNotFoundException err) {
					err.printStackTrace();
					System.out.println("Error finding the file");
				} catch (IOException err) {
					err.printStackTrace();
					System.out.println("Error reading the file");
				}
			}
		}

		System.out.println(byter.length);
		return byter;
	}

	/**
	 * Sends a byte[] over the network.
	 * 
	 * @param toSend,
	 *            the byte[] to send
	 * @param locationsOfFiles,
	 *            the points in the byte[] which denote new files
	 * @param stockNumber,
	 *            the label for these pictures in the database
	 * @param welcomeSocket,
	 *            the Server Socket that the tunnel is present on.
	 * 
	 */
	private static void sendData(byte[] toSend, List<Integer> locationsOfFiles, int stockNumber,
			ServerSocket welcomeSocket) {
		try {
			Socket connectionSocket = welcomeSocket.accept(); // wait
			// to
			// welcome
			// socket
			// for
			// client
			// connect

			System.out.println("Connected to client");

			OutputStream out = connectionSocket.getOutputStream();
			DataOutputStream dataSending = new DataOutputStream(out);

			int numberOfFiles = locationsOfFiles.size();

			dataSending.writeInt(toSend.length);
			dataSending.writeInt(stockNumber);
			dataSending.writeInt(numberOfFiles);
			for (int index = 0; index < numberOfFiles; index++) {
				dataSending.writeInt(locationsOfFiles.get(index));
			}

			System.out.println("Length of byte array is " + toSend.length + ", sending to client at "
					+ connectionSocket.getInetAddress());
			if (toSend.length > 0) {
				/*
				 * sends the byte array
				 */
				dataSending.write(toSend);
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/**
	 * Begins a sample clock out function for someone with the Timecard app.
	 */
	private static void startClockOut() {
		File folder = new File("test folder 3");
		try {
			String re = seeIfStillClockedIn(folder);
			System.out.println(re);
		} catch (IOException err) {
			err.printStackTrace();
		}
		try {
			Date today = new Date();
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
			String[] timeData = getClockOutTime("12/22/2013 12:33:17", formatter.format(today), 24);
			for (String entry : timeData) {
				System.out.println(entry);
			}
		} catch (ParseException err) {
			err.printStackTrace();
		}
	}

	/**
	 * this method will look at the CSV file inside the folder created for this
	 * instance of the Timecard object, and it returns the content of it. If
	 * there is content, the user is still logged in from a previous session. If
	 * not, the user is up to date with their clock-in, clock-out cycles.
	 * 
	 * @param folder
	 * @return the string with their clock in information
	 * @throws IOException
	 */
	private static String seeIfStillClockedIn(File folder) throws IOException {
		String csvPath = folder.getPath() + "/timecard1.csv";
		String csvLine = "";

		File csv = new File(csvPath);
		System.out.println("File is at " + csv.getPath());
		if (csv.exists()) {
			BufferedReader csvReader = new BufferedReader(new FileReader(csv));

			/*
			 * advances past the first line in the CSV and ignores it, this is
			 * the header
			 */
			csvReader.readLine();

			csvLine = csvReader.readLine();

			csvReader.close();
		}

		if (!csvLine.isEmpty()) {
			return csvLine;
		} else {
			return "the person is up-to-date";
		}
	}

	/**
	 * Compares the time difference between two times, and sees if it is shorter
	 * than a given maximum time.
	 * 
	 * @param earlier,
	 *            the earlier time
	 * @param later,
	 *            the later time
	 * @param maxClock,
	 *            the maximum amount of hours the difference can be
	 * @return the difference, or maximum amount of hours, whichever is shorter
	 * @throws ParseException,
	 *             in case the time strings are not formatted correctly
	 */
	private static String[] getClockOutTime(String earlier, String later, long maxClock) throws ParseException {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.US);
		Date earlyDate = format.parse(earlier);
		Date laterDate = format.parse(later);
		System.out.println("Clock in date is " + earlyDate + "\nTime now is " + laterDate);

		long millisMaxClock = ((maxClock * 60) * 60) * 1000;
		System.out.println(
				"Difference in milliseconds between clock in time and " + maxClock + " hours later: " + millisMaxClock);

		long differenceBetweenDates = laterDate.getTime() - earlyDate.getTime();
		System.out.println("Difference in milliseconds between clock in time and now: " + differenceBetweenDates);

		long differenceToUse;
		String clockOutDate;
		if (differenceBetweenDates < millisMaxClock) {
			differenceToUse = differenceBetweenDates;
			clockOutDate = later;
			System.out.println("Clocking out with current date and time.  Clock out date is set to " + clockOutDate);
			// use the current laterDate object to clock out with
		} else {
			laterDate.setTime(earlyDate.getTime() + millisMaxClock);
			differenceToUse = millisMaxClock;
			clockOutDate = format.format(laterDate);
			System.out.println("Clocking out with the maxClock value.  Clock out date is set to " + clockOutDate);
		}

		String formattedClockOutDate = clockOutDate.replace(' ', '^');
		double differenceInHours = ((Double.parseDouble("" + differenceToUse) / 1000) / 60) / 60;
		System.out.println("Difference in hours " + differenceInHours);

		formattedClockOutDate = formattedClockOutDate + '^' + differenceInHours;

		String[] timeData = formattedClockOutDate.split("\\^");
		return timeData;
	}

	/**
	 * Creates a new folder and file within it.
	 */
	private static void logInTextFile() {
		try {
			File file = new File("test folder/test.txt");
			file.createNewFile();
			BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));
			fileWriter.write("String 1" + System.lineSeparator());

			for (int i = 2; i < 1003; i++) {
				fileWriter.write("String " + i + System.lineSeparator());
			}

			fileWriter.close();

			System.out.println(file.getPath());

			File folder = new File("test folder 3");
			folder.mkdir();

			System.out.println(folder.getPath());
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	private static void addPictureInFolderToVFP9Database() {
		try {
			BufferedWriter fileMaker = new BufferedWriter(new FileWriter("pictures7/this.txt"));
			fileMaker.write("This is a test");
			fileMaker.close();

			fileMaker = new BufferedWriter(new FileWriter("pictures7/this2.txt"));
			fileMaker.write("127000");
			fileMaker.close();
		} catch (IOException err) {
			err.printStackTrace();
		}

		try {
			Runtime.getRuntime()
					.exec("\"C:/Documents and Settings/rainmaker/My" + " Documents/Workspace/Server3/export\" "
							+ "\"!T:/control5/ctrldbf5/images/pictures\" \"100002\" "
							+ "\"C:/Documents and Settings/rainmaker/My Documents/Workspace/Play/\"");
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	private static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");

		return format.format(date);
	}

}