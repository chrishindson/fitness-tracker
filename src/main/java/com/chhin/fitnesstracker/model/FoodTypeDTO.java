package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.FoodType;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
}
