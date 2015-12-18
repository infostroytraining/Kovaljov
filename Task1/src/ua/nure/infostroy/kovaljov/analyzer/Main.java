package ua.nure.infostroy.kovaljov.analyzer;

import com.beust.jcommander.JCommander;
import ua.nure.infostroy.kovaljov.jcommander.JCommanderProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		JCommanderProvider provider = new JCommanderProvider();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] argv = br.readLine().split(" ");
		new JCommander(provider, argv);
		String path = provider.input;
		boolean isParallel = provider.parallel;
		provider.task.performTask(path, isParallel);
	}
}
