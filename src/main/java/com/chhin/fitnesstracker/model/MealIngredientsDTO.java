package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;

public class MealIngredientsDTO {
  private String foodTypeId;
  private BigDecimal servingSize;

  public String getFoodTypeId() {
    return foodTypeId;
  }

  public void setFoodTypeId(String foodTypeId) {
    this.foodTypeId = foodTypeId;
  }

  public BigDecimal getServingSize() {
    return servingSize;
  }

  public void setServingSize(BigDecimal servingSize) {
    this.servingSize = servingSize;
  }
}
