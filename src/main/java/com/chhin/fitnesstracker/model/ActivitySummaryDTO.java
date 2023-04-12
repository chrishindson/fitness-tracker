package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class ActivitySummaryDTO {

  private String username;
  private LocalDate activityDate;
  private Long activityCount;
  private BigDecimal totalDistance;
  private LocalTime totalTime;
  private Long totalCalories;

  public ActivitySummaryDTO() {
  }

  public ActivitySummaryDTO(
      String username,
      LocalDate activityDate,
      BigDecimal totalDistance,
      Long activityCount,
      Long totalCalories,
      LocalTime totalTime) {
    this.username = username;
    this.activityDate = activityDate;
    this.activityCount = activityCount;
    this.totalDistance = totalDistance;
    this.totalCalories = totalCalories;
    this.totalTime = totalTime;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public LocalDate getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(LocalDate activityDate) {
    this.activityDate = activityDate;
  }

  public Long getActivityCount() {
    return activityCount;
  }

  public void setActivityCount(Long activityCount) {
    this.activityCount = activityCount;
  }

  public BigDecimal getTotalDistance() {
    return totalDistance;
  }

  public void setTotalDistance(BigDecimal totalDistance) {
    this.totalDistance = totalDistance;
  }

  public LocalTime getTotalTime() {
    return totalTime;
  }

  public void setTotalTime(LocalTime totalTime) {
    this.totalTime = totalTime;
  }

  public Long getTotalCalories() {
    return totalCalories;
  }

  public void setTotalCalories(Long totalCalories) {
    this.totalCalories = totalCalories;
  }
}
