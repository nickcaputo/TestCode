package main_threads;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import materials.Node;

import java.util.Scanner;

import utilities.StringCountComparator;
import utilities.Write;

public class PlayTest {

	public static void main(String[] args) {

		long currentTime = System.currentTimeMillis();
		
		PlayTest test = new PlayTest();

//		SQLRelated.runSQLQueriesOnDatabase();
		
		Write.writeLine(test.canObtain("A", "ABBABAABAA"));
		Write.writeLine(StringRelated.reverse("Irony"));

		long futureTime = System.currentTimeMillis();
		Write.writeLine("This took " + (futureTime - currentTime) + " ms.");
	}
	
	/**
	 * This calls the recursive obtain function to see if a string can be rearranged into another.
	 * @param initial, initial string
	 * @param target, what we want to see if it can be turned into
	 * @return, String saying "Possible" or "Impossible"
	 */
	private String canObtain(String initial, String target) {
		String result = obtain(initial, target);
		return result != null ? result : "Impossible";
	}
	
	private String obtain(String initial, String target) {
        if (initial.length() < target.length()) {
            String added = initial + 'A';
         	String switched = StringRelated.reverse(initial) + 'B';
            
            String result = obtain(added, target);
            if (result == null) {
                return obtain(switched, target);
            } else {
                return result;
            }
        }
        
        // called after strings are of equal length
        if (initial.equals(target)) {
        	return "Possible";
        } else {
        	return null;
        }
        
    }
	
	public static int days(int[] arrivals, int numPerDay) {
        double doublePerDay = numPerDay;
        int totalDays = 0; 
    	
        for (int arrival : arrivals) {
            double doubleArrival = arrival;
        	double daysItWillTake = Math.ceil(doubleArrival/doublePerDay);
            totalDays += daysItWillTake;
        }
        
        return totalDays;
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

		List<Entry<String, Integer>> valuesByCount = new ArrayList<Entry<String, Integer>>(
				hasher.entrySet());

		Collections.sort(valuesByCount, new StringCountComparator());

		for (Entry<String, Integer> entry : valuesByCount) {
			System.out.println(entry.toString());
		}

		console.close();
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
	 * Prints the label for the location in memory that this program is using.
	 */
	private static void getAndDisplayMemoryId() {
		String id = ManagementFactory.getRuntimeMXBean().getClassPath();
		System.out.println(id);
	}
	
//	/**
//	 * Test method which adds items from a collection to a HashMap to get an
//	 * idea of how many duplicates there are in the collection.
//	 */
//	private static void hashDuplicates() {
//		Map<Integer[], Integer> counter = new HashMap<>();
//		List<Integer[]> cellHistory = new ArrayList<>();
//
//		for (Integer[] item : cellHistory) {
//			Integer number = counter.get(item);
//			if (number == null) {
//				counter.put(item, 1);
//			} else {
//				counter.remove(item);
//				number++;
//
//				counter.put(item, number);
//			}
//
//			if (cellHistory.size() == counter.size()) {
//				Write.writeLine("Already most efficient path!");
//			} else {
//				// Write.writeLine("Can be more efficient");
//				// trim the ArrayList
//			}
//		}
//	}

}