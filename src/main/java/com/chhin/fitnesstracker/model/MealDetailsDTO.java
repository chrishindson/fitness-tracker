package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MealDetailsDTO {

  private Long foodTrackingId;
  private LocalDate foodTrackingDate;
  private Long mealTrackingId;
  private Long mealTypeId;
  private Long foodType;
  private BigDecimal mealServingSize;
  private String foodName;
  private BigDecimal foodServingSize;
  private BigDecimal foodCalories;

}
