package com.chhin.fitnesstracker.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

  public static LocalDate getLocalDate(String s) {
    if (s == null) {
      return LocalDate.now();
    }
    if (s.length() == 8) {
      return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyyMMdd"));
    }
    return null;
  }
}
