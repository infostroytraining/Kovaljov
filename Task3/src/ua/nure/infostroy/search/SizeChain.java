package ua.nure.infostroy.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

public class SizeChain extends Chain {

	private void doFullLogic(Scanner sc, Object parameter) {
		List<File> files = (List<File>) parameter;
		List<File> result = new ArrayList<>();
		long start = 0, finish = 0;
		System.out.print("������ �� ��������� �������� ? (0\1)");
		if (sc.hasNextInt() && sc.nextInt() == 1) {
			System.out.print("������� ��������� ������ � ������:");
			if (sc.hasNextLong()) {
				start = sc.nextLong();
			}
			System.out.print("������� �������� ������ � ������:");
			if (sc.hasNextLong()) {
				finish = sc.nextLong();
			}
			for (File file : files) {
				if (file.length() >= start && file.length() <= finish) {
					result.add(file);
				}
			}
			if (result.isEmpty()) {
				System.out.println("������ �� �������");
				return;
			}
			if (hasNext()) {
				nextChain.search(result);
			}
		} else if (hasNext()) {
			nextChain.search(parameter);
		} else {
			Utils.print(files);
		}
	}
	
	private void doEmptyLogic(Scanner sc, Object parameter){ 
		String root = (String) parameter;
		Collection<File> files = FileUtils.listFiles(new File(root), null, true);
		List<File> result = new ArrayList<>();
		long start = 0, finish = 0;
		System.out.print("������ �� ��������� �������� ? (0\1)");
		if (sc.hasNextInt() && sc.nextInt() == 1) {
			System.out.print("������� ��������� ������ � ������:");
			if (sc.hasNextLong()) {
				start = sc.nextLong();
			}
			System.out.print("������� �������� ������ � ������:");
			if (sc.hasNextLong()) {
				finish = sc.nextLong();
			}
			for (File file : files) {
				if (file.length() >= start && file.length() <= finish) {
					result.add(file);
				}
			}
			if (result.isEmpty()) {
				System.out.println("������ �� �������");
				return;
			}
			if (hasNext()) {
				nextChain.search(result);
			}
		} else if (hasNext()) {
			nextChain.search(parameter);
		} else {
			Utils.print(result);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void search(Object parameter) {
		Scanner sc = new Scanner(System.in);
		try {
			if (parameter instanceof List) {
				doFullLogic(sc, parameter);
			} else if (parameter instanceof String) {
				doEmptyLogic(sc,parameter);
			}
		} finally {
			sc.close();
		}
	}

}
