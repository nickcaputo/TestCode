package main_threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
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
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import utilities.StringCountComparator;
import utilities.Write;

public class PlayTest {
	
	private static final int PORT = 4248;

	public static void main(String[] args) {

		long currentTime = System.currentTimeMillis();

		// addToQueue();

		// getMaxProfit();

		Write.quickWrite(compress("abcdefggghhhiiijjjkkkklllmmmnnnoopppqqqrrrssstttuuuvvvwwwxxxyyyzzzzz"));

		Write.quickWrite(replaceSpaces("Mr Haim Style    ", 13)); // 0-1 ms
		
		

		// log3(81); // takes 3-7 ms

		// setConnectionToDatabase();

		// executeCalc30Seconds();

		// hasher();

		// play();

		long futureTime = System.currentTimeMillis();

		Write.quickWrite("This took " + (futureTime - currentTime) + " ms.");

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
	
	private void bitManipulate(long bit, int shift) {
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
	
	private void getAndDisplayMemoryId() {
		String id = ManagementFactory.getRuntimeMXBean().getClassPath();
		System.out.println(id);
	}
	
	private void testExperimentalRegex() {
		final String SEPARATOR = "§¶§";
		System.out.println(SEPARATOR);
		
		String test = "This" + SEPARATOR + SEPARATOR + "is" + SEPARATOR + 'a' + SEPARATOR + "string";
		
		String revised = test.replaceAll(SEPARATOR, System.lineSeparator());
		
		System.out.println(revised);
	}
	
	private void imageProcessing() {
		try {
			synchronized (this) {
				this.wait(1000);
			}
		} catch (InterruptedException err) {
			err.printStackTrace();
		}

		short fileCounter = 1;

		try {
			/*
			 * create welcoming socket at port on client
			 */
			ServerSocket welcomeSocket = new ServerSocket(PORT);
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

	private byte[] convertFileToBytes(int stockNumber, List<Integer> locationsOfFiles, int totalBytes) {
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

	private void sendData(byte[] toSend, List<Integer> locationsOfFiles, int stockNumber, ServerSocket welcomeSocket) {
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
	
	private void startClockOut() {
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
	private String seeIfStillClockedIn(File folder) throws IOException {
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

	private String[] getClockOutTime(String earlier, String later, long maxClock) throws ParseException {
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
			clockOutDate = formattedDate(laterDate, format);
			System.out.println("Clocking out with the maxClock value.  Clock out date is set to " + clockOutDate);
		}

		String formattedClockOutDate = clockOutDate.replace(' ', '^');
		double differenceInHours = ((Double.parseDouble("" + differenceToUse) / 1000) / 60) / 60;
		System.out.println("Difference in hours " + differenceInHours);

		formattedClockOutDate = formattedClockOutDate + '^' + differenceInHours;

		String[] timeData = formattedClockOutDate.split("\\^");
		return timeData;
	}

	private String formattedDate(Date date, DateFormat forTime) {
		String formattedDate = forTime.format(date);
		return formattedDate;
	}

	private void logInTextFile() {
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

	private void addPictureInFolderToVFP9Database() {
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
	
	private String getDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");
		
		return format.format(date);
	}

}