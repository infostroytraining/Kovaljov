package ua.nure.infostroy.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class ExtensionChain extends Chain{
	@Override
	@SuppressWarnings("unchecked")
	public void search(Object parameter) {
		Scanner sc = new Scanner(System.in);
		try {
			Collection<File> files = new ArrayList<>();
			if (parameter instanceof String) {
				files = FileUtils.listFiles(new File((String)parameter), null, true);
			}
			else if (parameter instanceof List) {
				files.addAll((List<File>)parameter);
			}
			List<File> result = new ArrayList<>();
			System.out.print("искать по расширению ? (0\1)");
			if (sc.hasNextInt() && sc.nextInt() == 1) {
				System.out.print("Введите расширение:");
				String extension = sc.next();
				for (File file : files) {
					if (FilenameUtils.getExtension(file.getName()).equals(extension)) {
						result.add(file);
					}
				}
				if (result.isEmpty()) {
					System.out.println("Ничего не найдено");
					return;
				}
				if (hasNext()) {
					nextChain.search(result);
				} 
			} else if (hasNext()){
				nextChain.search(parameter);
			}
			else {
				Utils.print(files);
			}
		} finally{
			sc.close();
		}
	}

}
