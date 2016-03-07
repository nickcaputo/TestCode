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
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import utilities.Constants;

public class IORelated {
	
	private static void bufferedReaderFromConsole() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.println("Ask a question!");
			String write = in.readLine();
			StringBuilder buffer = new StringBuilder();

			buffer.append(write);
			buffer.append(" is a pretty good question!");
			System.out.println(buffer.toString());
		} catch (IOException err) {
			err.printStackTrace();
		}
	}

	/**
	 * Uses a scanner to read from a file or system.in, printing the results.
	 * 
	 * @throws FileNotFoundException
	 */
	private static void readFromConsole() throws FileNotFoundException {
		Scanner console = new Scanner(new File("C:/TWL98.txt"));

		while (console.hasNextLine()) {
			System.out.println(StringRelated.compress(console.nextLine()));
		}
		System.out.println(StringRelated.compress("aaavvvppp"));

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
						System.out.println(image.getName() + " is "
								+ image.length() + " bytes.");
						fileCounter++;
						totalBytes = totalBytes + (int) image.length();
						image = new File(stockNumber + "-" + fileCounter
								+ ".jpg");
						// System.out.println("image exists " + image.exists());
					} else {
						break;
					}
				} while (image.exists());

				System.out.println(totalBytes);
				System.out.println(locationsOfFiles.size());

				byte[] files = convertFileToBytes(stockNumber,
						locationsOfFiles, totalBytes);
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
	 * @param stockNumber
	 *            , the label for the file
	 * @param locationsOfFiles
	 *            , the points in the array that denote new files
	 * @param totalBytes
	 *            , the total bytes in the array
	 * @return the byte array of all files combined
	 */
	private static byte[] convertFileToBytes(int stockNumber,
			List<Integer> locationsOfFiles, int totalBytes) {
		byte[] byter = new byte[totalBytes];
		int fileOffset = 0;

		for (int index = 0; index < locationsOfFiles.size(); index++) {
			File file = new File(stockNumber + "-" + (index + 1) + ".jpg");
			if (file.exists()) {
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					System.out.println("reading bytes starting at "
							+ fileOffset + " for "
							+ locationsOfFiles.get(index) + " bytes");
					fileInputStream.read(byter, fileOffset,
							locationsOfFiles.get(index));

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
	 * @param toSend
	 *            , the byte[] to send
	 * @param locationsOfFiles
	 *            , the points in the byte[] which denote new files
	 * @param stockNumber
	 *            , the label for these pictures in the database
	 * @param welcomeSocket
	 *            , the Server Socket that the tunnel is present on.
	 * 
	 */
	private static void sendData(byte[] toSend, List<Integer> locationsOfFiles,
			int stockNumber, ServerSocket welcomeSocket) {
		try {
			Socket connectionSocket = welcomeSocket.accept();

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

			System.out.println("Length of byte array is " + toSend.length
					+ ", sending to client at "
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
			DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",
					Locale.US);
			String[] timeData = getClockOutTime("12/22/2013 12:33:17",
					formatter.format(today), 24);
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
	 * @param earlier
	 *            , the earlier time
	 * @param later
	 *            , the later time
	 * @param maxClock
	 *            , the maximum amount of hours the difference can be
	 * @return the difference, or maximum amount of hours, whichever is shorter
	 * @throws ParseException
	 *             , in case the time strings are not formatted correctly
	 */
	private static String[] getClockOutTime(String earlier, String later,
			long maxClock) throws ParseException {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",
				Locale.US);
		Date earlyDate = format.parse(earlier);
		Date laterDate = format.parse(later);
		System.out.println("Clock in date is " + earlyDate + "\nTime now is "
				+ laterDate);

		long millisMaxClock = ((maxClock * 60) * 60) * 1000;
		System.out
				.println("Difference in milliseconds between clock in time and "
						+ maxClock + " hours later: " + millisMaxClock);

		long differenceBetweenDates = laterDate.getTime() - earlyDate.getTime();
		System.out
				.println("Difference in milliseconds between clock in time and now: "
						+ differenceBetweenDates);

		long differenceToUse;
		String clockOutDate;
		if (differenceBetweenDates < millisMaxClock) {
			differenceToUse = differenceBetweenDates;
			clockOutDate = later;
			System.out
					.println("Clocking out with current date and time.  Clock out date is set to "
							+ clockOutDate);
			// use the current laterDate object to clock out with
		} else {
			laterDate.setTime(earlyDate.getTime() + millisMaxClock);
			differenceToUse = millisMaxClock;
			clockOutDate = format.format(laterDate);
			System.out
					.println("Clocking out with the maxClock value.  Clock out date is set to "
							+ clockOutDate);
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
			BufferedWriter fileMaker = new BufferedWriter(new FileWriter(
					"pictures7/this.txt"));
			fileMaker.write("This is a test");
			fileMaker.close();

			fileMaker = new BufferedWriter(
					new FileWriter("pictures7/this2.txt"));
			fileMaker.write("127000");
			fileMaker.close();
		} catch (IOException err) {
			err.printStackTrace();
		}

		try {
			Runtime.getRuntime()
					.exec("\"C:/Documents and Settings/rainmaker/My"
							+ " Documents/Workspace/Server3/export\" "
							+ "\"!T:/control5/ctrldbf5/images/pictures\" \"100002\" "
							+ "\"C:/Documents and Settings/rainmaker/My Documents/Workspace/Play/\"");
		} catch (IOException err) {
			err.printStackTrace();
		}
	}
	
	

}
