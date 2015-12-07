package ua.nure.infostroy.kovaljov.analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer {
	private Pattern p = Pattern.compile("[A-Za-zА-Яа-я]+");

	public static void main(String[] args) throws IOException {
		IOHelper helper = new IOHelper();
		String input = helper.readLargeTextFile("input.txt");
		new Analyzer().getDuplicates(input);
	}

	private Map<String, Integer> sortByComparator(Map<String, Integer> unsortedMap,
			Comparator<Map.Entry<String, Integer>> comparator) {

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortedMap.entrySet());
		Collections.sort(list, comparator);
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<String, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}

	private Comparator<Map.Entry<String, Integer>> getFrequencyComparator() {
		return new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		};
	}

	private Comparator<Entry<String, Integer>> getAlphabeticalComparator() {
		return new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}

		};
	}

	private Comparator<String> getLengthComparator() {
		return new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		};
	}

	private void printFrequencyResult(Set<Entry<String, Integer>> collection) {
		for (Entry<String, Integer> entry : collection) {
			System.out.println(entry.getKey() + " ==> " + entry.getValue());
		}
	}

	private void printLengthResult(List<String> result) {
		for (String item : result) {
			System.out.println(item + " ==> " + item.length());
		}
	}

	private void printDuplicatesResult(Set<String> result) {
		for (String item : result) {
			System.out.println(new StringBuilder(item).reverse().toString());
		}
	}

	public void getFrequency(String text) {
		Matcher m = p.matcher(text);
		Map<String, Integer> wordToFrequency = new TreeMap<String, Integer>();
		while (m.find()) {
			String key = m.group().toLowerCase();
			if (wordToFrequency.containsKey(key)) {
				wordToFrequency.replace(key, wordToFrequency.get(key) + 1);
			} else {
				wordToFrequency.put(key, 1);
			}
		}
		wordToFrequency = sortByComparator(wordToFrequency, getFrequencyComparator());
		Set<Entry<String, Integer>> values = new TreeSet<>(getAlphabeticalComparator());
		int count = 0;
		for (Entry<String, Integer> entry : wordToFrequency.entrySet()) {
			if (count == 2) {
				break;
			}
			values.add(entry);
			count++;
		}
		printFrequencyResult(values);
	}

	public void getLength(String text) {

		Set<String> wordSet = new TreeSet<String>(getLengthComparator());
		Matcher m = p.matcher(text);
		while (m.find()) {
			String key = m.group().toLowerCase();
			wordSet.add(key);
		}
		List<String> result = new ArrayList<>();
		int count = 0;
		for (String item : wordSet) {
			if (count == 3) {
				printLengthResult(result);
				return;
			}
			result.add(item);
			count++;

		}
	}

	public void getDuplicates(String text) {
		Matcher m = p.matcher(text);
		List<String> wordList = new ArrayList<>();
		Set<String> result = new TreeSet<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (o1.equals(o2)) {
					return 0;
				}
				if (o1.length() == o2.length()) {
					return -1;
				}
				else {
					return o1.length()-o2.length();
				}
			}
		});
		while (m.find()) {
			String key = m.group().toLowerCase();
			
			if (result.size() == 3) {
				printDuplicatesResult(result);
				return;
			}
			if (wordList.contains(key)) {
				result.add(key);
			} else {
				wordList.add(key);
			}
		}
	}
}
