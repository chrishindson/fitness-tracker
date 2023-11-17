package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "stored_meal_ingredients")
public class StoredMealIngredients {

  @Id
  @Column(name = "stored_meal_ingredients_id")
  @SequenceGenerator(
      name = "stored_meal_ingredients_gen",
      sequenceName = "stored_meal_ingredients_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stored_meal_ingredients_gen")
  private Long storedMealIngredientsId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "stored_meal_id")
  private StoredMeal storedMeal;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "food_type_id")
  private FoodType foodType;

  @Column(name = "servings")
  private BigDecimal servings;
}
