package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.model.history.ActivityHistoryDTO;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDiaryDTO extends ActivityHistoryDTO {

  private LocalDate activityDate;

  public ActivityDiaryDTO(
      String username,
      LocalDate activityDate,
      Long activityCount,
      BigDecimal totalDistance,
      Integer totalCalories,
      LocalTime totalTime) {
    super(username, activityCount, totalDistance, totalCalories, totalTime);
    this.activityDate = activityDate;
  }
}
