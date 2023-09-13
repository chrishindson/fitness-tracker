package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
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

  public void setFoodTypeId(Long foodTypeId) {
    this.foodTypeId = foodTypeId;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public void setServingSize(BigDecimal servingSize) {
    this.servingSize = servingSize;
  }

  public void setCalories(BigDecimal calories) {
    this.calories = calories;
  }

  public void setTotalCarbohydrate(BigDecimal totalCarbohydrate) {
    this.totalCarbohydrate = totalCarbohydrate;
  }

  public void setTotalFat(BigDecimal totalFat) {
    this.totalFat = totalFat;
  }

  public void setProtein(BigDecimal protein) {
    this.protein = protein;
  }

  public void setSaturatedFat(BigDecimal saturatedFat) {
    this.saturatedFat = saturatedFat;
  }

  public void setTransFat(BigDecimal transFat) {
    this.transFat = transFat;
  }

  public void setCholesterol(BigDecimal cholesterol) {
    this.cholesterol = cholesterol;
  }

  public void setSodium(BigDecimal sodium) {
    this.sodium = sodium;
  }

  public void setPotassium(BigDecimal potassium) {
    this.potassium = potassium;
  }

  public void setDietaryFibre(BigDecimal dietaryFibre) {
    this.dietaryFibre = dietaryFibre;
  }

  public void setSugars(BigDecimal sugars) {
    this.sugars = sugars;
  }

  public void setVitaminA(BigDecimal vitaminA) {
    this.vitaminA = vitaminA;
  }

  public void setVitaminC(BigDecimal vitaminC) {
    this.vitaminC = vitaminC;
  }

  public void setCalcium(BigDecimal calcium) {
    this.calcium = calcium;
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
