package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MealIngredientsDTO {
  private String foodTypeId;
  private BigDecimal servingSize;
}
