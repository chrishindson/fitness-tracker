package com.chhin.fitnesstracker.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MealIngredientsDTO {
  private String foodTypeId;
  private BigDecimal servingSize;
}
