package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.MealIngredients;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealTrackingDTO {
  private String mealType;
  private List<MealIngredients> mealIngredients;
}
