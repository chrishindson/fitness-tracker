package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "meal_ingredients")
public class MealIngredients {

  @Id
  @Column(name = "meal_ingredients_id")
  @SequenceGenerator(name = "meal_ingredients_gen", sequenceName = "meal_ingredients_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_ingredients_gen")
  private Long mealIngredientsId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "meal_tracking_id")
  private MealTracking mealTracking;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "food_type_id")
  private FoodType foodType;

  @Column(name = "servings")
  private BigDecimal servings;

  public Long getMealIngredientsId() {
    return mealIngredientsId;
  }

  public void setMealIngredientsId(Long mealIngredientsId) {
    this.mealIngredientsId = mealIngredientsId;
  }

  public MealTracking getMealTracking() {
    return mealTracking;
  }

  public void setMealTracking(MealTracking mealTracking) {
    this.mealTracking = mealTracking;
  }

  public FoodType getFoodType() {
    return foodType;
  }

  public void setFoodType(FoodType foodType) {
    this.foodType = foodType;
  }

  public BigDecimal getServings() {
    return servings;
  }

  public void setServings(BigDecimal servings) {
    this.servings = servings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MealIngredients that = (MealIngredients) o;
    return Objects.equals(mealIngredientsId, that.mealIngredientsId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mealIngredientsId);
  }

  @Override
  public String toString() {
    return "MealIngredients{" +
        "mealIngredientsId=" + mealIngredientsId +
        ", mealTracking=" + mealTracking +
        ", foodType=" + foodType +
        ", servings=" + servings +
        '}';
  }
}
