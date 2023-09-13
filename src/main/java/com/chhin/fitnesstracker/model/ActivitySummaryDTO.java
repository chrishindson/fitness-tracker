package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivitySummaryDTO {

  private String username;
  private LocalDate activityDate;
  private Long activityCount;
  private BigDecimal totalDistance;
  private LocalTime totalTime;
  private Long totalCalories;

}
