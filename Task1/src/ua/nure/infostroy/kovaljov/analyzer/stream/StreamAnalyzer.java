package ua.nure.infostroy.kovaljov.analyzer.stream;

import ua.nure.infostroy.kovaljov.analyzer.Analyzable;
import ua.nure.infostroy.kovaljov.analyzer.Analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamAnalyzer implements Analyzable {
    private Pattern p = Pattern.compile("[A-Za-zА-Яа-я]+");
    @Override
    public String getFrequency(String path) {
        try {
           Map<String, Integer> map = Files.lines(Paths.get(path))
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(item -> p.matcher(item).find())
                    .map(item -> item.replaceAll("[^a-zA-ZА-Яа-яёЁ]", ""))
                    .collect(Collectors.groupingBy(Function.identity(),Collectors.summingInt(e -> 1) ))
                    .entrySet()
                    .stream()
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
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream)
                .filter(item -> p.matcher(item).find())
                .map(item -> item.replaceAll("[^a-zA-ZА-Яа-яёЁ]", ""))
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
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .filter(item -> p.matcher(item).find())
                    .map(item -> item.replaceAll("[^a-zA-ZА-Яа-яёЁ]", ""))
                    .map(item -> new StringBuilder(item).reverse().toString().toUpperCase())
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.summingInt(e -> 1)))
                    .entrySet()
                    .stream()
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
