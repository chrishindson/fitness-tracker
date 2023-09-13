package com.chhin.fitnesstracker.model;

import java.util.List;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoredMealDTO {
  private Long storedMealId;
  private String mealName;
  private List<MealIngredientsDTO> mealIngredientsDTOList;
  private Map<Long, String> foodTypeList;

  public String getMealIngredientDescription(Long foodTypeId) {

    return this.foodTypeList.get(foodTypeId);
  }
}
