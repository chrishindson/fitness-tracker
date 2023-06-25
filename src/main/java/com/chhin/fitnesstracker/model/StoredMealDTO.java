package com.chhin.fitnesstracker.model;

import java.util.List;
import java.util.Map;

public class StoredMealDTO {
  private Long storedMealId;
  private String mealName;
  private List<MealIngredientsDTO> mealIngredientsDTOList;
  private Map<Long, String> foodTypeList;

  public Long getStoredMealId() {
    return storedMealId;
  }

  public void setStoredMealId(Long storedMealId) {
    this.storedMealId = storedMealId;
  }

  public String getMealName() {
    return mealName;
  }

  public void setMealName(String mealName) {
    this.mealName = mealName;
  }

  public List<MealIngredientsDTO> getMealIngredientsDTOList() {
    return mealIngredientsDTOList;
  }

  public void setMealIngredientsDTOList(List<MealIngredientsDTO> mealIngredientsDTOList) {
    this.mealIngredientsDTOList = mealIngredientsDTOList;
  }

  public Map<Long, String> getFoodTypeList() {
    return foodTypeList;
  }

  public void setFoodTypeList(Map<Long, String> foodTypeList) {
    this.foodTypeList = foodTypeList;
  }

  public String getMealIngredientDescription(Long foodTypeId) {

    return this.foodTypeList.get(foodTypeId);
  }
}
