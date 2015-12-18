package ua.nure.infostroy.kovaljov.analyzer.parallel;

import ua.nure.infostroy.kovaljov.analyzer.Analyzable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class ParallelStreamAnalyzer implements Analyzable {
    private Pattern p = Pattern.compile("[A-Za-zР-пр-џ]+");
    @Override
    public String getFrequency(String path){
       return null;
    }

    @Override
    public String getLength(String path) {
        return null;
    }

    @Override
    public String getDuplicates(String path) {
        return null;
    }

}
