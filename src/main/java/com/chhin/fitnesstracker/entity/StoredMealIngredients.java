package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Entity
@Table(name = "stored_meal_ingredients")
public class StoredMealIngredients {

  @Id
  @Column(name = "stored_meal_ingredients_id")
  @SequenceGenerator(name = "stored_meal_ingredients_gen", sequenceName = "stored_meal_ingredients_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stored_meal_ingredients_gen")
  private Long storedMealIngredientsId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "stored_meal_id")
  private StoredMeal storedMeal;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "food_type_id")
  private FoodType foodType;

  @Column(name = "servings")
  private BigDecimal servings;

  public void setStoredMealIngredientsId(Long storedMealIngredientsId) {
    this.storedMealIngredientsId = storedMealIngredientsId;
  }

  public void setStoredMeal(StoredMeal storedMeal) {
    this.storedMeal = storedMeal;
  }

  public void setFoodType(FoodType foodType) {
    this.foodType = foodType;
  }

  public void setServings(BigDecimal servings) {
    this.servings = servings;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StoredMealIngredients that = (StoredMealIngredients) o;
    return Objects.equals(storedMealIngredientsId, that.storedMealIngredientsId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storedMealIngredientsId);
  }

  @Override
  public String toString() {
    return "StoredMealIngredients{" +
        "storedMealIngredientsId=" + storedMealIngredientsId +
        ", storedMeal=" + storedMeal +
        ", foodType=" + foodType +
        ", servings=" + servings +
        '}';
  }
}
