package ua.nure.infostroy.kovaljov.analyzer.factory;

import ua.nure.infostroy.kovaljov.analyzer.Analyzable;
import ua.nure.infostroy.kovaljov.analyzer.Analyzer;
import ua.nure.infostroy.kovaljov.analyzer.parallel.ParallelStreamAnalyzer;
import ua.nure.infostroy.kovaljov.analyzer.stream.StreamAnalyzer;

public class AnalyzerFactory {

    public Analyzable getAnalyzer(String whichAnalyzer) {
        switch (whichAnalyzer) {
            case "parallel": {
                return new ParallelStreamAnalyzer();
            }
            case "stream":{
                return new StreamAnalyzer();
            }
            default: {
                return new Analyzer();
            }
        }
    }
}
