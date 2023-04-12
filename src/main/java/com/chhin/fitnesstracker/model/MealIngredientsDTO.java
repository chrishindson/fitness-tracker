package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;

public class MealIngredientsDTO {
  private FoodTypeDTO foodTypeDTO;
  private BigDecimal servingSize;

  public FoodTypeDTO getFoodTypeDTO() {
    return foodTypeDTO;
  }

  public void setFoodTypeDTO(FoodTypeDTO foodTypeDTO) {
    this.foodTypeDTO = foodTypeDTO;
  }

  public BigDecimal getServingSize() {
    return servingSize;
  }

  public void setServingSize(BigDecimal servingSize) {
    this.servingSize = servingSize;
  }
}
