package main_threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

import utilities.Write;

public class ArrayRelated {

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

	/**
	 *
	 * Given an unsorted array of integers, sorts them by splitting it up into
	 * negatives and positives, where both are in order. Ex: {-1, 3, 2, -3, -2,
	 * 1} is turned into {-1, -3, -2, 3, 2, 1}
	 * 
	 * @param array,
	 *            unsorted array
	 * @return sorted array by halves
	 */
	public static int[] negPosSort(int[] array) {
		Queue<Integer> positives = new LinkedList<>();
		Queue<Integer> negatives = new LinkedList<>();

		for (int value : array) {
			if (value >= 0) { // positive
				positives.add(value);
			} else { // negative
				negatives.add(value);
			}
		}

		int[] sorted = new int[array.length];
		int place = 0;

		for (int value : negatives) {
			sorted[place] = value;
			place++;
		}

		for (int value : positives) {
			sorted[place] = value;
			place++;
		}

		return sorted;
	}
	
	public static int findLargest(int[] values) {
		if (values.length == 2) {
			return 2;
		} else {
			TreeSet<Integer> set = new TreeSet<>();
			
			for (int item : values) {
				set.add(item);
			}
			
			System.out.println(set);
			
			return 2;
		
		}
	}

}
