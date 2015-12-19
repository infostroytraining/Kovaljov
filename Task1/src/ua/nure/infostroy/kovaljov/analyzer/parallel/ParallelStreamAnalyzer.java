package ua.nure.infostroy.kovaljov.analyzer.parallel;

import ua.nure.infostroy.kovaljov.analyzer.Analyzable;
import ua.nure.infostroy.kovaljov.analyzer.Analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ParallelStreamAnalyzer implements Analyzable {
    private Pattern p = Pattern.compile("[A-Za-zÀ-ßà-ÿ]+");
    @Override
    public String getFrequency(String path){
        try {
            Map<String, Integer> map = Files.lines(Paths.get(path))
                    .parallel()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(item -> p.matcher(item).find())
                    .map(item -> item.replaceAll("[^a-zA-ZÀ-ßà-ÿ¸¨]", ""))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)))
                    .entrySet()
                    .parallelStream()
                    .sorted(Collections.reverseOrder(Comparator.comparing(Map.Entry::getKey)))
                    .limit(2)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));
            return Analyzer.printFrequencyResult(map.entrySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getLength(String path) {
        try {
            Map<String, Integer> map = Files.lines(Paths.get(path))
                    .parallel()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(item -> p.matcher(item).find())
                    .map(item -> item.replaceAll("[^a-zA-ZÀ-ßà-ÿ¸¨]", ""))
                    .distinct()
                    .sorted((e1, e2) -> e2.length()-e1.length())
                    .limit(3)
                    .collect(Collectors.toMap(item -> item, item -> item.length()));
            return Analyzer.printFrequencyResult(map.entrySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getDuplicates(String path) {
        try {
            Map<String, Integer> map = Files.lines(Paths.get(path))
                    .parallel()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(item -> p.matcher(item).find())
                    .map(item -> item.replaceAll("[^a-zA-ZÀ-ßà-ÿ¸¨]", ""))
                    .map(item -> new StringBuilder(item).reverse().toString().toUpperCase())
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.summingInt(e -> 1)))
                    .entrySet()
                    .parallelStream()
                    .filter(item -> item.getValue() > 1)
                    .limit(3)
                    .sorted((e1, e2) -> e1.getKey().length() - e2.getKey().length())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            return Analyzer.printFrequencyResult(map.entrySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
