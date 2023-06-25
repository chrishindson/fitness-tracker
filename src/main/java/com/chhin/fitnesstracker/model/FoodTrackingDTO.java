package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FoodTrackingDTO {
  private DateDTO foodTrackingDate;
  private Long mealType;
  private Map<Long, String> mealTypeList;
  private Map<Long, String> foodTypeList;
  private List<MealTrackingDTO> mealTrackingDTOList;
  private List<MealDetailsDTO> mealDetailsDTOList;
  private List<MealIngredientsDTO> mealIngredientsDTOList;

  public List<MealDetailsDTO> getMealDetailsDTOList() {
    return mealDetailsDTOList;
  }

  public void setMealDetailsDTOList(List<MealDetailsDTO> mealDetailsDTOList) {
    this.mealDetailsDTOList = mealDetailsDTOList;
  }

  public DateDTO getFoodTrackingDate() {
    return foodTrackingDate;
  }

  public void setFoodTrackingDate(DateDTO foodTrackingDate) {
    this.foodTrackingDate = foodTrackingDate;
  }

  public Long getMealType() {
    return mealType;
  }

  public void setMealType(Long mealType) {
    this.mealType = mealType;
  }

  public Map<Long, String> getMealTypeList() {
    return mealTypeList;
  }

  public void setMealTypeList(Map<Long, String> mealTypeList) {
    this.mealTypeList = mealTypeList;
  }

  public Map<Long, String> getFoodTypeList() {
    return foodTypeList;
  }

  public void setFoodTypeList(Map<Long, String> foodTypeList) {
    this.foodTypeList = foodTypeList;
  }

  public List<MealTrackingDTO> getMealTrackingDTOList() {
    return mealTrackingDTOList;
  }

  public void setMealTrackingDTOList(List<MealTrackingDTO> mealTrackingDTOList) {
    this.mealTrackingDTOList = mealTrackingDTOList;
  }

  public String getMealTypeDisplay() {
    return this.mealTypeList.get(this.mealType);
  }

  public String getMealDetails(Long mealTypeId) {
    List<MealDetailsDTO> dtoList = getMealDetailsForMealType(mealTypeId);
    if (dtoList.isEmpty()) {
      return null;
    }
    BigDecimal totalCalories = BigDecimal.ZERO;
    StringBuilder items = new StringBuilder();
    for (MealDetailsDTO dto : dtoList) {
      totalCalories = totalCalories.add(dto.getFoodCalories().multiply(dto.getMealServingSize()));
      items.append(dto.getFoodName()).append("\n");
    }
    return items + "\n\nCalories: " + totalCalories;
  }

  public List<MealDetailsDTO> getMealDetailsForMealType(Long mealTypeId) {
    if (this.mealDetailsDTOList == null) {
      return Collections.emptyList();
    }
    return this.mealDetailsDTOList.stream().filter(x -> x.getMealTypeId().equals(mealTypeId)).toList();
  }

  public String getMealIngredientDescription(Long foodTypeId) {
    return this.foodTypeList.get(foodTypeId);
  }

  public List<MealIngredientsDTO> getMealIngredientsDTOList() {
    return mealIngredientsDTOList;
  }

  public void setMealIngredientsDTOList(List<MealIngredientsDTO> mealIngredientsDTOList) {
    this.mealIngredientsDTOList = mealIngredientsDTOList;
  }
}
