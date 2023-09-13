package com.chhin.fitnesstracker.util;

import com.chhin.fitnesstracker.entity.StoredMealIngredients;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JsonUtils {

  public static String listAsJson(List<StoredMealIngredients> listToJson) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();

    return mapper.writeValueAsString(listToJson);
  }

  public static List<StoredMealIngredients> jsonToList(String jsonString) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(jsonString, new TypeReference<>() {
    });
  }
}
