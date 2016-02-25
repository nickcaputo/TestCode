package main_threads;

import java.util.*;

class Solution {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();

		runThis();

		long completeAt = System.currentTimeMillis();

		Write.writeLine("This took " + (completeAt - startTime) + " ms.");

	}

	private static void runThis() {
		Solution s = new Solution();
		double power3 = Math.pow(3, 14);
		s.print(compress("aaaaabbbbbcccccdddddeeeee"));
		s.printConsole();
		Write.writeLine(s.log3(power3));
		String pass = "";
	}

	
	/**
	// Three Step Path

	// Given a website request log, compute the most common three step path
	// taken by users.

	// For example, given this request log:

	// UserID,Path
	// 1,/home
	// 1,/search
	// 2,/home
	// 1,/product
	// 2,/browse
	// 2,/product
	// 3,/home
	// 1,/cart
	// 3,/search
	// 3,/product

	// The most common 3 step path would be /home -> /search -> /product
	// 1: home search product
	// 1: search product cart
	// 2: home browse productS
	*/
	public static String findMostCommon(Scanner console) {
		HashMap<String, String> map = new HashMap<>();
		HashMap<Integer, String> worker = new HashMap<>(); // key is user, value
															// is path route for
															// user

		// get first line of request log, find userID, find all paths taken by
		// this user
		while (console.hasNextLine()) {
			String line = console.nextLine();
			String[] idAndPath = line.split(",");
			// idAndPath[0] == userId

			if (map.get(idAndPath[0]) == null) {
				map.put(idAndPath[0], idAndPath[1]);
			} else {
				String previousPath = map.get(idAndPath[0]);
				String concatPath = previousPath + idAndPath[1];

				// this will work if the log contains sets where each userId has 3 paths taken.
				// a slight modification can allow us to accommodate values larger than 3.
				char[] array = concatPath.toCharArray();
				int slashes = 0;
				for (char element : array) {
					if (element == '/') {
						slashes++;
					}

					if (slashes >= 3) {
						worker.put(1, concatPath); // adds element to map
					}
				}

				map.remove(idAndPath[0]);
				map.put(idAndPath[0], concatPath);
			}

		}
		
		
		int count = 1;
		String pathToReturn = "";
		while (worker.get(count) != null) { // iterates until we find the
											// highest count
			pathToReturn = worker.get(count);
			count++;
		}

		return pathToReturn;
	}

	/**
	 * 
	 */
	public void print(String message) {
		Write.writeLine(message);
		HashMap<Integer, Character> map = new HashMap<>();
		map.put(1, 'a');
	}

	/**
	 * Opens a Scanner object and reads from System.in or from a file.
	 */
	private void printConsole() {
		Scanner console = new Scanner(System.in);

		while (console.hasNextLine()) {
			Write.quickWrite(compress(console.nextLine()));
		}

		console.close();
	}

	private double log3(double number) {
		return Math.log(number) / Math.log(3);
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
}

class Write {
	public static void writeLine(String message) {
		System.out.println(System.currentTimeMillis() + " | " + message);
	}

	public static void writeLine(double message) {
		writeLine("" + message);
	}

	public static void quickWrite(String message) {
		System.out.println(message);
	}

	public static void quickWrite(double message) {
		System.out.println("" + message);
	}
}