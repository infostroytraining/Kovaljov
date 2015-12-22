package ua.nure.infostroy.search;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Utils {
	public static void print(List<File> files) {
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}
	public static void print(Collection<File> files) {
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	public static Collection getFiles(String root) {
		return FileUtils.listFiles(new File(root), null, true);
	}
}
