package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "meal_tracking")
public class MealTracking {

  @Id
  @Column(name = "meal_tracking_id")
  @SequenceGenerator(name = "meal_tracking_gen", sequenceName = "meal_tracking_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_tracking_gen")
  private Long mealTrackingId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "food_tracking_id")
  private FoodTracking foodTracking;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "meal_type_id")
  private MealType mealType;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "meal_tracking_id", referencedColumnName = "meal_tracking_id")
  private List<MealIngredients> mealIngredientsList;

  public Long getMealTrackingId() {
    return mealTrackingId;
  }

  public void setMealTrackingId(Long mealTrackingId) {
    this.mealTrackingId = mealTrackingId;
  }

  public FoodTracking getFoodTracking() {
    return foodTracking;
  }

  public void setFoodTracking(FoodTracking foodTracking) {
    this.foodTracking = foodTracking;
  }

  public MealType getMealType() {
    return mealType;
  }

  public void setMealType(MealType mealType) {
    this.mealType = mealType;
  }

  public List<MealIngredients> getMealIngredientsList() {
    return mealIngredientsList;
  }

  public void setMealIngredientsList(List<MealIngredients> mealIngredientsList) {
    this.mealIngredientsList = mealIngredientsList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MealTracking that = (MealTracking) o;
    return Objects.equals(mealTrackingId, that.mealTrackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mealTrackingId);
  }

  @Override
  public String toString() {
    return "MealTracking{" +
        "mealTypeId=" + mealTrackingId +
        ", foodTracking=" + foodTracking +
        ", mealType=" + mealType +
        '}';
  }

}
