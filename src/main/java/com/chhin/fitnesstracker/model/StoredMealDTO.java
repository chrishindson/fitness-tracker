package com.chhin.fitnesstracker.model;

import java.util.List;

public class StoredMealDTO {
  private Long storedMealId;
  private String mealName;
  private List<MealIngredientsDTO> mealIngredientsDTOList;
  private List<SelectOptionsDTO> foodTypeList;

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

  public List<SelectOptionsDTO> getFoodTypeList() {
    return foodTypeList;
  }

  public void setFoodTypeList(List<SelectOptionsDTO> foodTypeList) {
    this.foodTypeList = foodTypeList;
  }

  public String getMealIngredientDescription(Long foodTypeId) {
    SelectOptionsDTO dto = this.foodTypeList.stream().filter(x -> x.getId().equals(foodTypeId)).findFirst().orElse(null);
    if (dto == null) {
      return null;
    }
    return dto.getDescription();

  }
}
