package ua.nure.infostroy.kovaljov.analyzer;

import java.io.IOException;

import ua.nure.infostroy.kovaljov.command.Command;
import ua.nure.infostroy.kovaljov.command.DuplicatesCommand;
import ua.nure.infostroy.kovaljov.command.FrequencyCommand;
import ua.nure.infostroy.kovaljov.command.LengthCommand;

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
	
	public void performTask(String path) throws IOException {
		command.execute(path);
	}
}