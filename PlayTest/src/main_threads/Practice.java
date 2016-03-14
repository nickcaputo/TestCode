package main_threads;

import java.util.*;

import utilities.Write;

public class Practice {

	public static boolean isSingleRiffle(int[] half1, int[] half2, int[] deck) {
		int half1Top = 0;
		int half2Top = 0;
		
		// check to make sure that both halves combine to be the same length as the full deck
		if ((half1.length + half2.length) == deck.length) {
			
			// iterate through the deck
			for (int card : deck) {
				int first = -1;
				int second = -1;

				// check if we have space left in the first half
				if (half1Top < half1.length) {
					first = half1[half1Top];
				}

				// check if we have space left in the second half
				if (half2Top < half2.length) {
					second = half2[half2Top];
				}

				// if the top card in the deck is not equal to the top card of
				// the first or second half, it is not a single riffle
				if (card != first && card != second) {
					
					Write.writeLine("first is " + first + "\nSecond is " + second + "\nCard is " + card);
					return false;
				} else {

					// increment the top counters
					if (card == first) {
						half1Top++;
					} else if (card == second) {
						half2Top++;
					}

				}
			}
		}

		// if we make it through here, we have iterated through the entire deck
		// and both halves.
		return true;
	}

	/**
	 * // example key = "TRADINGFEW" // example code = "LGXWEV" // deciphered
	 * int = 709 Could be more efficient by dynamically multiplying digits by
	 * 10, 100, etc. then adding them all together, instead of creating a new
	 * integer from a string.
	 * 
	 * @param key
	 * @param code
	 * @return the deciphered code
	 */
	public static int decodeIntegerFromString(String key, String code) {
		char[] lettersInKey = key.toCharArray();
		char[] codeArray = code.toCharArray();
		String output = "0";

		// checking the inputs
		if (lettersInKey.length != 10) {
			throw new IllegalArgumentException("Must have key of length 10");
		}

		// construct the output
		for (char item : codeArray) {
			// iterate through lettersInKey finding it
			for (int i = 0; i < lettersInKey.length; i++) {
				int toShow = ((i + 1) % 10);
				if (lettersInKey[i] == item) {
					output += "" + toShow;
				}
			}
		}

		return new Integer(output);
	}

	/**
	 * Opens a Scanner object and reads from System.in or from a file.
	 */
	private void printConsole() {
		Scanner console = new Scanner(System.in);

		while (console.hasNextLine()) {
			Write.quickWrite(StringRelated.compress(console.nextLine()));
		}

		console.close();
	}

	/**
	 * Calculates, given x and y coordinates for a given set of missiles coming
	 * in at the user, the first target they miss at if it takes 1 second to
	 * move in the X or Y direction 1 value. Returns -1 if all the targets are
	 * hit!
	 * 
	 * @param xs
	 * @param ys
	 * @param times
	 * @return
	 */
	private static int turretDefense(int[] xs, int[] ys, int[] times) {
		int setX = 0;
		int setY = 0;
		int time = 0;
		// int difference;

		for (int i = 0; i < xs.length; i++) {
			int moveX = Math.abs(setX - xs[i]);
			int moveY = Math.abs(setY - ys[i]);
			int timeToMove = times[i] - time;

			if ((moveX + moveY) > timeToMove) {
				return i;
			}

			setX = moveX;
			setY = moveY;
			time = (moveX + moveY);

		}
		return -1;
	}

	/**
	 * Given a set of stock values, finds the maximum profit we could make by
	 * selling them at any point.
	 * 
	 * @param stocks
	 *            , the list of stocks to check for the maximum profit
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
	 * Returns true if the second string is a rotation of the input
	 * 
	 * @param original
	 * @param suspectedRotation
	 * @return
	 */
	public static boolean isStringRotation(String original, String suspectedRotation) {
		StringBuilder builder = new StringBuilder(original);
		builder.append(original);
		return builder.toString().contains(suspectedRotation);
	}

	// /**
	// // Three Step Path
	//
	// // Given a website request log, compute the most common three step path
	// // taken by users.
	//
	// // For example, given this request log:
	//
	// // UserID,Path
	// // 1,/home
	// // 1,/search
	// // 2,/home
	// // 1,/product
	// // 2,/browse
	// // 2,/product
	// // 3,/home
	// // 1,/cart
	// // 3,/search
	// // 3,/product
	//
	// // The most common 3 step path would be /home -> /search -> /product
	// // 1: home search product
	// // 1: search product cart
	// // 2: home browse productS
	// *
	// * This could be completed.
	// */
	// public static String findMostCommon(Scanner console) {
	// HashMap<String, String> map = new HashMap<>();
	// HashMap<Integer, String> worker = new HashMap<>(); // key is user, value
	// // is path route for
	// // user
	//
	// // get first line of request log, find userID, find all paths taken by
	// // this user
	// while (console.hasNextLine()) {
	// String line = console.nextLine();
	// String[] idAndPath = line.split(",");
	// // idAndPath[0] == userId
	//
	// if (map.get(idAndPath[0]) == null) {
	// map.put(idAndPath[0], idAndPath[1]);
	// } else {
	// String previousPath = map.get(idAndPath[0]);
	// String concatPath = previousPath + idAndPath[1];
	//
	// // this will work if the log contains sets where each userId has 3 paths
	// taken.
	// // a slight modification can allow us to accommodate values larger than
	// 3.
	// char[] array = concatPath.toCharArray();
	// int slashes = 0;
	// for (char element : array) {
	// if (element == '/') {
	// slashes++;
	// }
	//
	// if (slashes >= 3) {
	// worker.put(1, concatPath); // adds element to map
	// }
	// }
	//
	// map.remove(idAndPath[0]);
	// map.put(idAndPath[0], concatPath);
	// }
	//
	// }
	//
	//
	// int count = 1;
	// String pathToReturn = "";
	// while (worker.get(count) != null) { // iterates until we find the
	// // highest count
	// pathToReturn = worker.get(count);
	// count++;
	// }
	//
	// return pathToReturn;
	// }
}