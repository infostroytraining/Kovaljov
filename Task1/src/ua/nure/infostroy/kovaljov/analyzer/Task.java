package ua.nure.infostroy.kovaljov.analyzer;

import java.io.IOException;

public enum Task {
	FREQUENCY("frequency"),LENGTH("length"),DUPLICATES("duplicates");
	
	private String value;
	
	Task(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public void performTask(String path) throws IOException {
		Analyzer analyzer = new Analyzer();
		IOHelper helper = new IOHelper();
		String input = helper.readLargeTextFile(path);
		switch(this.value) {
		case "frequency":{
			analyzer.getFrequency(input);
			break;
		}
		case "length":{
			analyzer.getLength(input);
			break;
		}
		case "duplicates":{
			analyzer.getDuplicates(input);
			break;
		}default:{
			return;
		}
		}
	}
}