package main_threads;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeRelated {

	/**
	 * Returns true if the specified year is a leap year (is divisible by 4
	 * except when both divisible by 100 and not by 400).
	 * 
	 * @param year,
	 *            a year (ex. 2028) @return, if it is a leap year
	 */
	public static boolean isALeapYear(int year) {
		return (year % 4 == 0) && !(year % 100 == 0 && year % 400 != 0);
	}

	/**
	 * If the date actually will exist in a calendar, this method returns true
	 * 
	 * @param year,
	 *            a year
	 * @param month,
	 *            a month between 1-12
	 * @param day,
	 *            a day between 1-31
	 * @return true if the date is legal
	 */
	public static boolean isALegalDate(int year, int month, int day) {
		// check date legality
		if ((month <= 7 && month % 2 == 0) || (month >= 8 && month % 2 == 1)) {
			if (month == 2) {
				if (isALeapYear(year)) {
					// System.out.println("Is a february in leap year");
					return day <= 29;
				} else {
					// System.out.println("Is a feburary in a normal year");
					return day <= 28;
				}
			}

			// System.out.println("Is an even month before July (but not
			// February) or an odd month after August");
			return day <= 30;
		} else {

			// System.out.println("Is an even month after August, or odd month
			// before july");
			return day <= 31;
		}
	}
	


	private static String getDateTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss");

		return format.format(date);
	}

}
