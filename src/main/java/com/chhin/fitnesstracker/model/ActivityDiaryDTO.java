package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityDiaryDTO extends ActivityHistoryDTO {

  private LocalDate activityDate;

  public ActivityDiaryDTO() {
  }

  public ActivityDiaryDTO(LocalDate activityDate) {
    this.activityDate = activityDate;
  }

  public ActivityDiaryDTO(String username, LocalDate activityDate, Long activityCount,
                          BigDecimal totalDistance, Integer totalCalories, LocalTime totalTime) {
    super(username, activityCount, totalDistance, totalCalories, totalTime);
    this.activityDate = activityDate;
  }

  public LocalDate getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(LocalDate activityDate) {
    this.activityDate = activityDate;
  }

  @Override
  public String toString() {
    return "ActivityDiaryDTO{" +
        "activityDate=" + activityDate +
        "', " + super.toString() + "'}'";
  }
}
