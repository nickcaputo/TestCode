package main_threads;

import java.util.Random;

import utilities.Write;

public class NumberRelated {
	

	/**
	 * Method testing the effects of bit manipulation.
	 * 
	 * @param bit
	 *            , the number to be represented in binary
	 * @param shift
	 *            , how much to shift this bit left/right
	 */
	private static void bitManipulate(long bit, int shift) {
		System.out.println("Input integer (binary): "
				+ Long.toBinaryString(bit));
		System.out.println("Input shift (binary)  : "
				+ Long.toBinaryString(shift) + System.lineSeparator());

		long shiftLeft = bit << shift;
		long shiftRight = bit >> shift;
		long inclusiveOr = bit | shift;
		long exclusiveOr = bit ^ shift;
		long and = bit & shift;

		System.out.println("Shifting by " + shift + " bits.");
		System.out.println("Shift left : " + shiftLeft);
		System.out.println("Shift right: " + shiftRight
				+ System.lineSeparator());

		System.out.println("And (decimal): " + and);
		System.out.println("And (binary) : " + Long.toBinaryString(and)
				+ System.lineSeparator());

		System.out.println("Inclusive or (decimal): " + inclusiveOr);
		System.out.println("Inclusive or (binary) : "
				+ Long.toBinaryString(inclusiveOr));
		System.out.println("Exclusive or (decimal): " + exclusiveOr);
		System.out.println("Exclusive or (binary) : "
				+ Long.toBinaryString(exclusiveOr));
	}

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

	/**
	 * Uses the random number generator to print random numbers from 1-16.
	 */
	private static void randomNumbers() {
		Random rand = new Random();

		for (int i = 0; i <= 16; i++) {
			System.out.println(rand.nextInt(16) + 1);
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
	 * @param number
	 *            , the number to log
	 * @return the number in log base 3
	 */
	public static double log3(double number) {
		double log = (Math.log(number) / Math.log(3));
		System.out.println(log);
		return log;
	}

}
