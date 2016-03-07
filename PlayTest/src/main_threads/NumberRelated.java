package main_threads;

import utilities.Write;

public class NumberRelated {

	/**
	 * Gets the mean of a set of numbers, provided the sum and the size of the
	 * region. (This is more meant to experiment with float/double/int/long
	 * conversion in math. More of just dumb curiosity.
	 * 
	 * @return the mean
	 */
	public static float getMean(int sum, int size) {
		float mean = sum / size;
		Write.quickWrite("Mean is " + mean);
		return mean;
	}

}
