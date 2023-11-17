package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "meal_tracking")
public class MealTracking {

  @Id
  @Column(name = "meal_tracking_id")
  @SequenceGenerator(
      name = "meal_tracking_gen",
      sequenceName = "meal_tracking_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_tracking_gen")
  private Long mealTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "food_tracking_id")
  private FoodTracking foodTracking;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meal_type_id")
  private MealType mealType;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "meal_tracking_id", referencedColumnName = "meal_tracking_id")
  private List<MealIngredients> mealIngredientsList;
}
