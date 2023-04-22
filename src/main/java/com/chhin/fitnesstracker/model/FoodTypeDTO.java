package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entities.FoodType;

import java.math.BigDecimal;

public class FoodTypeDTO {

  private Long foodTypeId;
  private String foodName;
  private BigDecimal servingSize;
  private BigDecimal calories;
  private BigDecimal totalCarbohydrate;
  private BigDecimal totalFat;
  private BigDecimal protein;
  private BigDecimal saturatedFat;
  private BigDecimal transFat;
  private BigDecimal cholesterol;
  private BigDecimal sodium;
  private BigDecimal potassium;
  private BigDecimal dietaryFibre;
  private BigDecimal sugars;
  private BigDecimal vitaminA;
  private BigDecimal vitaminC;
  private BigDecimal calcium;
  private BigDecimal iron;

  public FoodTypeDTO() {
  }

  public FoodTypeDTO(FoodType foodType) {
    this.foodTypeId = foodType.getFoodTypeId();
    this.foodName = foodType.getFoodName();
    this.servingSize = foodType.getServingSize();
    this.calories = foodType.getCalories();
    this.totalCarbohydrate = foodType.getTotalCarbohydrate();
    this.totalFat = foodType.getTotalFat();
    this.protein = foodType.getProtein();
    this.saturatedFat = foodType.getSaturatedFat();
    this.transFat = foodType.getTransFat();
    this.cholesterol = foodType.getCholesterol();
    this.sodium = foodType.getSodium();
    this.potassium = foodType.getPotassium();
    this.dietaryFibre = foodType.getDietaryFibre();
    this.sugars = foodType.getSugars();
    this.vitaminA = foodType.getVitaminA();
    this.vitaminC = foodType.getVitaminC();
    this.calcium = foodType.getCalcium();
    this.iron = foodType.getIron();
  }

  public Long getFoodTypeId() {
    return foodTypeId;
  }

  public void setFoodTypeId(Long foodTypeId) {
    this.foodTypeId = foodTypeId;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public BigDecimal getServingSize() {
    return servingSize;
  }

  public void setServingSize(BigDecimal servingSize) {
    this.servingSize = servingSize;
  }

  public BigDecimal getCalories() {
    return calories;
  }

  public void setCalories(BigDecimal calories) {
    this.calories = calories;
  }

  public BigDecimal getTotalCarbohydrate() {
    return totalCarbohydrate;
  }

  public void setTotalCarbohydrate(BigDecimal totalCarbohydrate) {
    this.totalCarbohydrate = totalCarbohydrate;
  }

  public BigDecimal getTotalFat() {
    return totalFat;
  }

  public void setTotalFat(BigDecimal totalFat) {
    this.totalFat = totalFat;
  }

  public BigDecimal getProtein() {
    return protein;
  }

  public void setProtein(BigDecimal protein) {
    this.protein = protein;
  }

  public BigDecimal getSaturatedFat() {
    return saturatedFat;
  }

  public void setSaturatedFat(BigDecimal saturatedFat) {
    this.saturatedFat = saturatedFat;
  }

  public BigDecimal getTransFat() {
    return transFat;
  }

  public void setTransFat(BigDecimal transFat) {
    this.transFat = transFat;
  }

  public BigDecimal getCholesterol() {
    return cholesterol;
  }

  public void setCholesterol(BigDecimal cholesterol) {
    this.cholesterol = cholesterol;
  }

  public BigDecimal getSodium() {
    return sodium;
  }

  public void setSodium(BigDecimal sodium) {
    this.sodium = sodium;
  }

  public BigDecimal getPotassium() {
    return potassium;
  }

  public void setPotassium(BigDecimal potassium) {
    this.potassium = potassium;
  }

  public BigDecimal getDietaryFibre() {
    return dietaryFibre;
  }

  public void setDietaryFibre(BigDecimal dietaryFibre) {
    this.dietaryFibre = dietaryFibre;
  }

  public BigDecimal getSugars() {
    return sugars;
  }

  public void setSugars(BigDecimal sugars) {
    this.sugars = sugars;
  }

  public BigDecimal getVitaminA() {
    return vitaminA;
  }

  public void setVitaminA(BigDecimal vitaminA) {
    this.vitaminA = vitaminA;
  }

  public BigDecimal getVitaminC() {
    return vitaminC;
  }

  public void setVitaminC(BigDecimal vitaminC) {
    this.vitaminC = vitaminC;
  }

  public BigDecimal getCalcium() {
    return calcium;
  }

  public void setCalcium(BigDecimal calcium) {
    this.calcium = calcium;
  }

  public BigDecimal getIron() {
    return iron;
  }

  public void setIron(BigDecimal iron) {
    this.iron = iron;
  }
}
