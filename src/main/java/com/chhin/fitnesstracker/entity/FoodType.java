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
@Table(name = "food_type")
public class FoodType {

  @Id
  @Column(name = "food_type_id")
  @SequenceGenerator(name = "food_type_gen", sequenceName = "food_type_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_type_gen")
  private Long foodTypeId;

  @Column(name = "food_name", nullable = false, length = 200)
  private String foodName;

  @Column(name = "serving_size", precision = 4, nullable = false)
  private BigDecimal servingSize;

  @Column(name = "calories", precision = 4, nullable = false)
  private BigDecimal calories;

  @Column(name = "total_carbohydrate", precision = 4)
  private BigDecimal totalCarbohydrate;

  @Column(name = "total_fat", precision = 4)
  private BigDecimal totalFat;

  @Column(name = "protein", precision = 4)
  private BigDecimal protein;

  @Column(name = "saturated_fat", precision = 4)
  private BigDecimal saturatedFat;

  @Column(name = "trans_fat", precision = 4)
  private BigDecimal transFat;

  @Column(name = "cholesterol", precision = 4)
  private BigDecimal cholesterol;

  @Column(name = "sodium", precision = 4)
  private BigDecimal sodium;

  @Column(name = "potassium", precision = 4)
  private BigDecimal potassium;

  @Column(name = "dietary_fibre", precision = 4)
  private BigDecimal dietaryFibre;

  @Column(name = "sugars", precision = 4)
  private BigDecimal sugars;

  @Column(name = "vitamin_a", precision = 4)
  private BigDecimal vitaminA;

  @Column(name = "vitamin_c", precision = 4)
  private BigDecimal vitaminC;

  @Column(name = "calcium", precision = 4)
  private BigDecimal calcium;

  @Column(name = "iron", precision = 4)
  private BigDecimal iron;
}
