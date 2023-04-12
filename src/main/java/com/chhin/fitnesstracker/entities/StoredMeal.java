package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "stored_meal")
public class StoredMeal {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stored_meal_gen")
  @SequenceGenerator(name = "stored_meal_gen", sequenceName = "stored_meal_seq", allocationSize = 1)
  @Column(name = "stored_meal_id", nullable = false)
  private Long storedMealId;

  @Column(name = "stored_meal_name", nullable = false, length = 100)
  private String storedMealName;

  public Long getStoredMealId() {
    return storedMealId;
  }

  public void setStoredMealId(Long storedMealId) {
    this.storedMealId = storedMealId;
  }

  public String getStoredMealName() {
    return storedMealName;
  }

  public void setStoredMealName(String storedMealName) {
    this.storedMealName = storedMealName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StoredMeal that = (StoredMeal) o;
    return Objects.equals(storedMealId, that.storedMealId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(storedMealId);
  }
}