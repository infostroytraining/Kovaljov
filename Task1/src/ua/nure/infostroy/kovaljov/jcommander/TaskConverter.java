package ua.nure.infostroy.kovaljov.jcommander;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;
import ua.nure.infostroy.kovaljov.analyzer.Task;

public class TaskConverter implements IStringConverter<Task>{

	@Override
	public Task convert(String value) {
		Task convertedValue = Task.fromString(value);
		 
        if(convertedValue == null) {
            throw new ParameterException("Value " + value + "can not be converted to Task. " +
                    "Available values are: frequency, length, duplicates.");
        }
        return convertedValue;
	}

}
