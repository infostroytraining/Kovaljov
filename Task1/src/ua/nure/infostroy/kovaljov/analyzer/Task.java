package ua.nure.infostroy.kovaljov.analyzer;

public enum Task {
	FREQUENCY("frequency"),LENGTH("length"),DUPLICATES("duplicates");
	
	private String value;
	Task(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}