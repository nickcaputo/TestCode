package main_threads;

public class StringRelated {

	/**
	 * Compresses a string by removing repeating characters and replacing them
	 * with the character followed by the amount of times it is repeated.
	 * Example: Input: "aaaaaaabbbbbcccxxxyyyzzz", Output: "a7b5c3x3y3z3"
	 * 
	 * @param input
	 *            , String to compress @return, compressed string, or the same
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
	 * Tests an experimental regular expression for use in TCP/IP.
	 */
	private static void testExperimentalRegex() {
		final String SEPARATOR = "§¶§";
		System.out.println(SEPARATOR);

		String test = "This" + SEPARATOR + SEPARATOR + "is" + SEPARATOR + 'a'
				+ SEPARATOR + "string";

		String revised = test.replaceAll(SEPARATOR, System.lineSeparator());

		System.out.println(revised);
	}

	/**
	 * Takes a string object as a parameter, and returns a reversed. Example:
	 * Input: "Ripley", Output: "yelpiR"
	 * 
	 * @param string
	 *            , string to reverse
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
	 * @param input
	 *            , the string to replace
	 * @param inputLength
	 *            , the length of the substring to consider when replacing
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
	
}
