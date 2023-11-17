package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "meal_type")
public class MealType {

  @Id
  @Column(name = "meal_type_id")
  @SequenceGenerator(name = "meal_type_gen", sequenceName = "meal_type_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_type_gen")
  private Long mealTypeId;

  @Column(name = "meal_type_description", nullable = false, unique = true, length = 100)
  private String mealTypeDescription;
}
