package ua.nure.infostroy.kovaljov.analyzer;

public interface Analyzable {
	String getFrequency(String file);
	String getLength(String file);
	String getDuplicates(String file);
}
