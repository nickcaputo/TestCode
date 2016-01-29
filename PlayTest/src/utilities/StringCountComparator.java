package utilities;

import java.util.Comparator;
import java.util.Map.Entry;

public class StringCountComparator implements Comparator<Entry<String, Integer>> {

	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {

		return o1.getValue() < o2.getValue() ? 1 : o1.getValue() == o2.getValue() ? 0 : -1;
	}

}
