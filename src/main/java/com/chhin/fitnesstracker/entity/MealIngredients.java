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
@Table(name = "meal_ingredients")
public class MealIngredients {

  @Id
  @Column(name = "meal_ingredients_id")
  @SequenceGenerator(
      name = "meal_ingredients_gen",
      sequenceName = "meal_ingredients_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_ingredients_gen")
  private Long mealIngredientsId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meal_tracking_id")
  private MealTracking mealTracking;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "food_type_id")
  private FoodType foodType;

  @Column(name = "servings")
  private BigDecimal servings;
}
