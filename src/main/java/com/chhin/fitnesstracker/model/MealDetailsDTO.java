package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MealDetailsDTO {

  private Long foodTrackingId;
  private LocalDate foodTrackingDate;
  private Long mealTrackingId;
  private Long mealTypeId;
  private Long foodType;
  private BigDecimal mealServingSize;
  private String foodName;
  private BigDecimal foodServingSize;
  private BigDecimal foodCalories;

  public MealDetailsDTO(Long foodTrackingId,
                        LocalDate foodTrackingDate,
                        Long mealTrackingId,
                        Long mealTypeId,
                        Long foodType,
                        BigDecimal mealServingSize,
                        String foodName,
                        BigDecimal foodServingSize,
                        BigDecimal foodCalories) {
    this.foodTrackingId = foodTrackingId;
    this.foodTrackingDate = foodTrackingDate;
    this.mealTrackingId = mealTrackingId;
    this.mealTypeId = mealTypeId;
    this.foodType = foodType;
    this.mealServingSize = mealServingSize;
    this.foodName = foodName;
    this.foodServingSize = foodServingSize;
    this.foodCalories = foodCalories;
  }

  public Long getFoodTrackingId() {
    return foodTrackingId;
  }

  public void setFoodTrackingId(Long foodTrackingId) {
    this.foodTrackingId = foodTrackingId;
  }

  public LocalDate getFoodTrackingDate() {
    return foodTrackingDate;
  }

  public void setFoodTrackingDate(LocalDate foodTrackingDate) {
    this.foodTrackingDate = foodTrackingDate;
  }

  public Long getMealTrackingId() {
    return mealTrackingId;
  }

  public void setMealTrackingId(Long mealTrackingId) {
    this.mealTrackingId = mealTrackingId;
  }

  public Long getMealTypeId() {
    return mealTypeId;
  }

  public void setMealTypeId(Long mealTypeId) {
    this.mealTypeId = mealTypeId;
  }

  public Long getFoodType() {
    return foodType;
  }

  public void setFoodType(Long foodType) {
    this.foodType = foodType;
  }

  public BigDecimal getMealServingSize() {
    return mealServingSize;
  }

  public void setMealServingSize(BigDecimal mealServingSize) {
    this.mealServingSize = mealServingSize;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public BigDecimal getFoodServingSize() {
    return foodServingSize;
  }

  public void setFoodServingSize(BigDecimal foodServingSize) {
    this.foodServingSize = foodServingSize;
  }

  public BigDecimal getFoodCalories() {
    return foodCalories;
  }

  public void setFoodCalories(BigDecimal foodCalories) {
    this.foodCalories = foodCalories;
  }
}
