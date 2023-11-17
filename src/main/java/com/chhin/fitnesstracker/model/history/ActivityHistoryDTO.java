package com.chhin.fitnesstracker.model.history;

import java.math.BigDecimal;
import java.time.LocalTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ActivityHistoryDTO {

  private String username;
  private Long activityCount;
  private BigDecimal totalDistance;
  private LocalTime totalTime;
  private Integer totalCalories;

  public ActivityHistoryDTO(
      String username,
      Long activityCount,
      BigDecimal totalDistance,
      Integer totalCalories,
      LocalTime totalTime) {
    this.username = username;
    this.activityCount = activityCount;
    this.totalDistance = totalDistance;
    this.totalCalories = totalCalories;
    this.totalTime = totalTime;
  }
}
