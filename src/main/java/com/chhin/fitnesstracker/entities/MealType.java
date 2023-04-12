package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "meal_type")
public class MealType {

  @Id
  @Column(name = "meal_type_id")
  @SequenceGenerator(name = "meal_type_gen", sequenceName = "meal_type_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_type_gen")
  private Long mealTypeId;

  @Column(name = "meal_type_description", nullable = false, unique = true, length = 100)
  private String mealTypeDescription;

  public Long getMealTypeId() {
    return mealTypeId;
  }

  public void setMealTypeId(Long mealTypeId) {
    this.mealTypeId = mealTypeId;
  }

  public String getMealTypeDescription() {
    return mealTypeDescription;
  }

  public void setMealTypeDescription(String mealTypeDescription) {
    this.mealTypeDescription = mealTypeDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MealType that = (MealType) o;
    return Objects.equals(mealTypeId, that.mealTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mealTypeId);
  }
}
