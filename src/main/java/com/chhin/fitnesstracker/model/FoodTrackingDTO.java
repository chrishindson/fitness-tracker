package com.chhin.fitnesstracker.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FoodTrackingDTO {
  private DateDTO foodTrackingDate;
  private Long mealType;
  private Map<Long, String> mealTypeList;
  private Map<Long, String> foodTypeList;
  private List<MealTrackingDTO> mealTrackingDTOList;
  private List<MealDetailsDTO> mealDetailsDTOList;
  @Getter
  private List<MealIngredientsDTO> mealIngredientsDTOList;


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

  public void setMealIngredientsDTOList(List<MealIngredientsDTO> mealIngredientsDTOList) {
    this.mealIngredientsDTOList = mealIngredientsDTOList;
  }
}
