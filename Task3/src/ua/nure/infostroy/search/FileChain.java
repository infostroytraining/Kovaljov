package ua.nure.infostroy.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class FileChain extends Chain {

	@Override
	@SuppressWarnings("unchecked")
	public void search(Object parameter) {
		List<File> result = new ArrayList<File>();
		Scanner sc = new Scanner(System.in);
		try {
			System.out.print("искать по имени файла ? (0/1)");
			if (sc.hasNextInt() && sc.nextInt() == 1) {
				if (parameter instanceof String) {
					doEmptyLogic(sc, result, (String)parameter);
				}
				if (parameter instanceof List) {
					doFullLogic(sc, result, (List<File>)parameter);
					}
			} else if (hasNext()) {
				nextChain.search(parameter);
			} else {
				System.out.print("Ничего не найдено");
				return;
			}
		} finally {
			sc.close();
		}
	}

	private void doFullLogic(Scanner sc, List<File> result, List<File> files) {
		System.out.print("Введите имя файла: ");
		String fileName = sc.next();
		Iterator<File> iterator = files.iterator();
		while (iterator.hasNext()) {
			File file = iterator.next();
			if (file.getName().contains(fileName)) {
				result.add(file);
			}
		}
		if (result.isEmpty()) {
			System.out.print("Ничего не найдено");
		} else if (hasNext()) {
			nextChain.search(result);
		} else {
			Utils.print(result);
		}
	}

	private void doEmptyLogic(Scanner sc, List<File> result, String rootDirectory) {
		System.out.print("Введите имя файла: ");
		String fileName = sc.next();
		Collection<File> files = FileUtils.listFiles(new File(rootDirectory), null, true);
		Iterator<File> iterator = files.iterator();
		while (iterator.hasNext()) {
			File file = iterator.next();
			if (file.getName().contains(fileName)) {
				result.add(file);
			}
		}
		if (result.isEmpty()) {
			System.out.print("Ничего не найдено");
		} else if (hasNext()) {
			nextChain.search(result);
		} else {
			Utils.print(result);
		}
	}

}
