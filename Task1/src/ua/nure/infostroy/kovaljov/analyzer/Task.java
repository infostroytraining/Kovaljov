package ua.nure.infostroy.kovaljov.analyzer;

import ua.nure.infostroy.kovaljov.analyzer.factory.AnalyzerFactory;
import ua.nure.infostroy.kovaljov.command.Command;
import ua.nure.infostroy.kovaljov.command.DuplicatesCommand;
import ua.nure.infostroy.kovaljov.command.FrequencyCommand;
import ua.nure.infostroy.kovaljov.command.LengthCommand;

import java.io.IOException;

public enum Task {
	FREQUENCY(new FrequencyCommand()),LENGTH(new LengthCommand()),DUPLICATES(new DuplicatesCommand());
	
	private Command command;
	
	Task(Command value) {
		this.command = value;
	}
	
	public static Task fromString(String code) {
		 
        for(Task output : Task.values()) {
            if(output.toString().equalsIgnoreCase(code)) {
                return output;
            }
        }
        return null;
    }
	
	public void performTask(String path, boolean isParallel) throws IOException {
		command.execute(path,new AnalyzerFactory().getAnalyzer(isParallel? "stream":""));
	}
}