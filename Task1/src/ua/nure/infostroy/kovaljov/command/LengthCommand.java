package ua.nure.infostroy.kovaljov.command;

import ua.nure.infostroy.kovaljov.analyzer.Analyzable;
import ua.nure.infostroy.kovaljov.analyzer.IOHelper;

import java.io.IOException;

public class LengthCommand implements Command{

	@Override
	public void execute(String path,Analyzable analyzer) {
			analyzer.getLength(path);
	}

}
