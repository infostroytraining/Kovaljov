package ua.nure.infostroy.kovaljov.command;

import java.io.IOException;

import ua.nure.infostroy.kovaljov.analyzer.Analyzer;
import ua.nure.infostroy.kovaljov.analyzer.IOHelper;

public class DuplicatesCommand implements Command {

	@Override
	public void execute(String path) {
		try {
			Analyzer analyzer = new Analyzer();
			String text = "";
			text = new IOHelper().readLargeTextFile(path);
			analyzer.getDuplicates(text);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
