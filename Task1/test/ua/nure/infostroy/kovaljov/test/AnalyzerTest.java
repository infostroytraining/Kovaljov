package ua.nure.infostroy.kovaljov.test;

import org.junit.Test;
import ua.nure.infostroy.kovaljov.analyzer.Analyzer;

import static org.junit.Assert.*;

public class AnalyzerTest {

	private Analyzer analyzer = new Analyzer();
	private final String INPUT = "as more as better. As soon as possible. "
			+ "East or west home is best. Soon soon. No better better more wild west";

	@Test
	public void testDuplicates() {
		StringBuilder sb = new StringBuilder();
		sb.append("sa").append(System.lineSeparator()).append("noos")
			.append(System.lineSeparator()).append("retteb");
		assertEquals(sb.toString(), analyzer.getDuplicates(INPUT));
	}

	@Test
	public void testLength() {
		StringBuilder sb = new StringBuilder();
		sb.append("possible ==> 8").append(System.lineSeparator()).append("better ==> 6")
			.append(System.lineSeparator()).append("more ==> 4");
		assertEquals(sb.toString(), analyzer.getLength(INPUT));
	}

	@Test
	public void testFrequency() {
		StringBuilder sb = new StringBuilder();
		sb.append("better ==> 3").append(System.lineSeparator()).append("as ==> 4");
		assertEquals(sb.toString(), analyzer.getFrequency(INPUT));
	}

}
