package ua.nure.infostroy.kovaljov.analyzer;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyzer implements Analyzable{
	private Pattern p = Pattern.compile("[A-Za-zА-Яа-я]+");

	public static Map<String, Integer> sortByComparator(Map<String, Integer> unsortedMap,
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

	public static Comparator<Map.Entry<String, Integer>> getFrequencyComparator() {
		return new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		};
	}

	public static Comparator<Entry<String, Integer>> getAlphabeticalComparator() {
		return new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o2.getKey().compareTo(o1.getKey());
			}

		};
	}

	public static Comparator<String> getLengthComparator() {
		return new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.length() - o1.length();
			}
		};
	}

	public static String printFrequencyResult(Set<Entry<String, Integer>> collection) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, Integer> entry : collection) {
			System.out.println(entry.getKey() + " ==> " + entry.getValue());
			sb.append(entry.getKey());
			sb.append(" == > ");
			sb.append(entry.getValue());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public static String printLengthResult(List<String> result) {
		StringBuilder sb = new StringBuilder();
		for (String item : result) {
			System.out.println(item + " ==> " + item.length());
			sb.append(item);
			sb.append(" == > ");
			sb.append(item.length());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	private String printDuplicatesResult(Set<String> result) {
		StringBuilder sb = new StringBuilder();
		for (String item : result) {
			sb.append(new StringBuilder(item).reverse().toString());
			sb.append(System.lineSeparator());
		}
		System.out.println(sb.toString());
		return sb.toString();
	}
	private String getText(String path) throws IOException{
		String text;
		text = new IOHelper().readLargeTextFile(path);
		return text;
	}
	public String getFrequency(String path) {
		String text = "";
		try {
			text = getText(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long startTime = System.currentTimeMillis();
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
		long finish = System.currentTimeMillis();
		System.out.println("Elapsed time of #getFrequency:" + (finish - startTime));
		return printFrequencyResult(values);
	}

	public String getLength(String path) {
		String text = "";
		try {
			text = getText(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long startTime = System.currentTimeMillis();
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
				long finish = System.currentTimeMillis();
				System.out.println("Elapsed time of #GetLength:" + (finish - startTime));
				return printLengthResult(result);
			}
			result.add(item);
			count++;
		}
		return "";
	}

	public String getDuplicates(String path) {
		String text = "";
		try {
			text = getText(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long startTime = System.currentTimeMillis();
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
				} else {
					return o1.length() - o2.length();
				}
			}
		});
		while (m.find()) {
			String key = m.group().toLowerCase();

			if (result.size() == 3) {
				long finish = System.currentTimeMillis();
				System.out.println("Elapsed time of #getDuplicates: " + (finish - startTime));
				return printDuplicatesResult(result);
			}
			if (wordList.contains(key)) {
				result.add(key);
			} else {
				wordList.add(key);
			}
		}
		return "";
	}
}
