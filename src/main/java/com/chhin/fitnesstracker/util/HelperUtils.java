package com.chhin.fitnesstracker.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HelperUtils {
    public static LinkedHashMap<Long, String> getSortedMap(Map<Long, String> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue,
                                LinkedHashMap::new));
    }
}
