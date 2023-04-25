package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "food_type")
public class FoodType {

  @Id
  @Column(name = "food_type_id")
  @SequenceGenerator(name = "food_type_gen", sequenceName = "food_type_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_type_gen")
  private Long foodTypeId;

  @Column(name = "food_name", nullable = false, length = 200)
  private String foodName;

  @Column(name = "serving_size", precision = 4, nullable = false)
  private BigDecimal servingSize;
  @Column(name = "calories", precision = 4, nullable = false)
  private BigDecimal calories;
  @Column(name = "total_carbohydrate", precision = 4)
  private BigDecimal totalCarbohydrate;
  @Column(name = "total_fat", precision = 4)
  private BigDecimal totalFat;
  @Column(name = "protein", precision = 4)
  private BigDecimal protein;
  @Column(name = "saturated_fat", precision = 4)
  private BigDecimal saturatedFat;
  @Column(name = "trans_fat", precision = 4)
  private BigDecimal transFat;
  @Column(name = "cholesterol", precision = 4)
  private BigDecimal cholesterol;
  @Column(name = "sodium", precision = 4)
  private BigDecimal sodium;
  @Column(name = "potassium", precision = 4)
  private BigDecimal potassium;
  @Column(name = "dietary_fibre", precision = 4)
  private BigDecimal dietaryFibre;
  @Column(name = "sugars", precision = 4)
  private BigDecimal sugars;
  @Column(name = "vitamin_a", precision = 4)
  private BigDecimal vitaminA;
  @Column(name = "vitamin_c", precision = 4)
  private BigDecimal vitaminC;
  @Column(name = "calcium", precision = 4)
  private BigDecimal calcium;
  @Column(name = "iron", precision = 4)
  private BigDecimal iron;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FoodType that = (FoodType) o;
    return Objects.equals(foodTypeId, that.foodTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(foodTypeId);
  }
}
