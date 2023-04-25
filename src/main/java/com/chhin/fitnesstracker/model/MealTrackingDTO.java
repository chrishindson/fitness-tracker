package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.MealIngredients;

import java.util.List;

public class MealTrackingDTO {
  private String mealType;
  private List<MealIngredients> mealIngredients;

  public String getMealType() {
    return mealType;
  }

  public void setMealType(String mealType) {
    this.mealType = mealType;
  }

  public List<MealIngredients> getMealIngredients() {
    return mealIngredients;
  }

  public void setMealIngredients(List<MealIngredients> mealIngredients) {
    this.mealIngredients = mealIngredients;
  }
}
