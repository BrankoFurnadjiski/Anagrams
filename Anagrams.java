package Anagrams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {

    private static Map<Map<String, Integer>, Set<String>> totalWords;

    public static void main(String[] args) {
        findAll(System.in);
    }

    public static void findAll(InputStream inputStream) {
        // Vasiod kod ovde
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        totalWords = new HashMap<>();

        bufferedReader.lines()
                    .forEach(word -> {
                        String[] parts = word.split("");
                        Map<String, Integer> map = new HashMap<>();
                        Arrays.stream(parts)
                                .forEach(letter -> {
                                    //map.computeIfAbsent(letter, l -> map.put(l, 0));
                                    if(!map.containsKey(letter))
                                        map.put(letter, 0);
                                    int value = map.get(letter) + 1;
                                    map.put(letter, value);
                                });
                        //totalWords.computeIfAbsent(map, m -> totalWords.put(map, new TreeSet<>()));
                        if(!totalWords.containsKey(map))
                            totalWords.put(map, new TreeSet<>());
                        totalWords.get(map).add(word);
                    });

        totalWords.values().stream()
                    .map(set ->  set.stream().collect(Collectors.joining(" ")))
                    .sorted()
                    .forEach(System.out::println);
    }
}
