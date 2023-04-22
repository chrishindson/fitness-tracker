package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FoodTrackingDTO {
  private DateDTO foodTrackingDate;
  private String mealType;
  private List<SelectOptionsDTO> mealTypeList;
  private List<SelectOptionsDTO> foodTypeList;
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

  public String getMealType() {
    return mealType;
  }

  public void setMealType(String mealType) {
    this.mealType = mealType;
  }

  public List<SelectOptionsDTO> getMealTypeList() {
    return mealTypeList;
  }

  public void setMealTypeList(List<SelectOptionsDTO> mealTypeList) {
    this.mealTypeList = mealTypeList;
  }

  public List<SelectOptionsDTO> getFoodTypeList() {
    return foodTypeList;
  }

  public void setFoodTypeList(List<SelectOptionsDTO> foodTypeList) {
    this.foodTypeList = foodTypeList;
  }

  public List<MealTrackingDTO> getMealTrackingDTOList() {
    return mealTrackingDTOList;
  }

  public void setMealTrackingDTOList(List<MealTrackingDTO> mealTrackingDTOList) {
    this.mealTrackingDTOList = mealTrackingDTOList;
  }

  public List<SelectOptionsDTO> getSortedMealTypeList() {
    return this.mealTypeList.stream().sorted(Comparator.comparing(SelectOptionsDTO::getId)).collect(Collectors.toList());
  }

  public String getMealTypeDisplay() {
    SelectOptionsDTO dto = this.mealTypeList.stream().filter(x -> x.getId().equals(Long.valueOf(this.mealType))).findFirst().orElse(null);

    if (dto == null) {
      return null;
    }
    return dto.getDescription();
  }

  public String getMealDetails(Long mealTypeId) {
    List<MealDetailsDTO> dtoList = getMealDetailsForMealType(mealTypeId);
    if (dtoList.size() == 0) {
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
      return null;
    }
    return this.mealDetailsDTOList.stream().filter(x -> x.getMealTypeId().equals(mealTypeId)).collect(Collectors.toList());
  }

  public String getMealIngredientDescription(Long foodTypeId) {
    SelectOptionsDTO dto = this.foodTypeList.stream().filter(x -> x.getId().equals(foodTypeId)).findFirst().orElse(null);
    if (dto == null) {
      return null;
    }
    return dto.getDescription();

  }

  public List<MealIngredientsDTO> getMealIngredientsDTOList() {
    return mealIngredientsDTOList;
  }

  public void setMealIngredientsDTOList(List<MealIngredientsDTO> mealIngredientsDTOList) {
    this.mealIngredientsDTOList = mealIngredientsDTOList;
  }
}
