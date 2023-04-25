package com.chhin.fitnesstracker.model.history;

import java.math.BigDecimal;
import java.time.LocalTime;

public class ActivityHistoryDTO {

  private String username;
  private Long activityCount;
  private BigDecimal totalDistance;
  private LocalTime totalTime;
  private Integer totalCalories;

  public ActivityHistoryDTO() {
  }

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public Integer getTotalCalories() {
    return totalCalories;
  }

  public void setTotalCalories(Integer totalCalories) {
    this.totalCalories = totalCalories;
  }

  @Override
  public String toString() {
    return "ActivityHistoryDTO{" +
        "username='" + username + '\'' +
        ", activityCount=" + activityCount +
        ", totalDistance=" + totalDistance +
        ", totalTime=" + totalTime +
        ", totalCalories=" + totalCalories +
        '}';
  }
}
