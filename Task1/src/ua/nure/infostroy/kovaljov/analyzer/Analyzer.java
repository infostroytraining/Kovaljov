package ua.nure.infostroy.kovaljov.analyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private static String path = "";

	public static void main(String[] args) throws IOException {
		getCommand();
	}

	private static void getCommand() throws IOException {
		InputStreamReader sr =new InputStreamReader(System.in);
		BufferedReader cnsl = new BufferedReader(sr);
		String command = "";
		while (!command.equals("exit")) {
			command = cnsl.readLine();
			if (command != null && command.equals("-- help")) {
				printHelp();
			} else if (command.startsWith("-i")) {
				try {
					path = command.substring(2, command.length());
				} catch (IndexOutOfBoundsException ex) {
					command = "exit";
					continue;
				}
			}
			else if (command.startsWith("--input")) {
				try {
					path = command.substring(7, command.length());
				} catch (IndexOutOfBoundsException ex) {
					command = "exit";
					continue;
				}
			}
			else if (command.startsWith("-t")) {
				try {
					if (path.length()<=1) {
						command="exit";
						continue;
					}
					Task.valueOf(command.substring(2, command.length())).performTask(path);
				} catch (IndexOutOfBoundsException | IOException ex) {
					command = "exit";
					continue;
				}
			}
			else if (command.startsWith("--task")) {
				try {
					if (path.length()<=1) {
						command="exit";
						continue;
					}
					Task.valueOf(command.substring("--task".length(), command.length())).performTask(path);
				} catch (IndexOutOfBoundsException | IOException ex) {
					command = "exit";
					continue;
				}
			}
		}
	}


	private static void printHelp() {
		System.out.println("1. -i (--input) - path to the input file "
				+ "(e.g. C:\\Program Files\\Java\\input.txt). Type: String, Required: true");
		System.out.println("2. -t (--task) – task to execute. "
				+ "Type: Enum, Required: true, Permitted values: frequency, " + "length, duplicates");
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

	private String printFrequencyResult(Set<Entry<String, Integer>> collection) {
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

	private String printLengthResult(List<String> result) {
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

	public String getFrequency(String text) {
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
		System.out.println("Elapsed time of #GetLength:" + (finish - startTime));
		return printFrequencyResult(values);
	}

	public String getLength(String text) {
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

	public String getDuplicates(String text) {
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
				System.out.println("Elapsed time: " + (finish - startTime));
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
