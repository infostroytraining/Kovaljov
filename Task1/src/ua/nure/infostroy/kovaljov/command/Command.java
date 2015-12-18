package ua.nure.infostroy.kovaljov.command;

import ua.nure.infostroy.kovaljov.analyzer.Analyzable;

public interface Command {
	public void execute(String path, Analyzable analyzer);            
}
