package main_threads;

import java.util.ArrayList;
import java.util.List;

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

}
