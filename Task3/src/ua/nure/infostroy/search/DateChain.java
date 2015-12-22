package ua.nure.infostroy.search;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class DateChain extends Chain{

	@SuppressWarnings("unchecked")
	@Override
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
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			Date start,finish;
			System.out.print("искать по диапазону дат ? (0\1)");
			if (sc.hasNextInt() && sc.nextInt() == 1) {
				System.out.print("Введите начальную дату в следующем виде: dd.MM.yyyy");
				start = format.parse(sc.next());
				System.out.print("Введите конечную дату в следующем виде: dd.MM.yyyy");
				finish = format.parse(sc.next());
				for (File file :files) {
					Path filePath = file.toPath();
					BasicFileAttributes attributes = null;
			        try
			        {
			            attributes =
			                    Files.readAttributes(filePath, BasicFileAttributes.class);
			        }
			        catch (IOException exception)
			        {
			            System.out.println("Exception handled when trying to get file " +
			                    "attributes: " + exception.getMessage());
			        }
			        long milliseconds = attributes.creationTime().to(TimeUnit.MILLISECONDS);
			        if (start.getTime()<=milliseconds && finish.getTime()>=milliseconds) {
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
		} catch (ParseException e) {
			e.printStackTrace();
		} finally{
			sc.close();
		}
	}

}
