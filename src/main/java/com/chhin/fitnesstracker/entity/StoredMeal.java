package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "stored_meal")
public class StoredMeal {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stored_meal_gen")
  @SequenceGenerator(
      name = "stored_meal_gen",
      sequenceName = "stored_meal_pk_seq",
      allocationSize = 1)
  @Column(name = "stored_meal_id", nullable = false)
  private Long storedMealId;

  @Column(name = "stored_meal_name", nullable = false, length = 100)
  private String storedMealName;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "stored_meal_id")
  private List<StoredMealIngredients> storedMealIngredientsList;

  public void setStoredMealId(Long storedMealId) {
    this.storedMealId = storedMealId;
  }

  public void setStoredMealName(String storedMealName) {
    this.storedMealName = storedMealName;
  }

  public void setStoredMealIngredientsList(List<StoredMealIngredients> storedMealIngredientsList) {
    this.storedMealIngredientsList = storedMealIngredientsList;
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
