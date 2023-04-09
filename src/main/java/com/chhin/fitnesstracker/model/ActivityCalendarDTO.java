package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityCalendarDTO extends ActivityHistoryDTO {
  private LocalDate activityDate;

  public ActivityCalendarDTO() {
  }

  public ActivityCalendarDTO(LocalDate activityDate) {
    this.activityDate = activityDate;
  }

  public ActivityCalendarDTO(String username, LocalDate activityDate, Long activityCount, BigDecimal totalDistance, Integer totalCalories, LocalTime totalTime) {
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
    return "ActivityCalendarDTO{" +
        "activityDate=" + activityDate +
        "', " + super.toString() + "'}'";
  }
}
