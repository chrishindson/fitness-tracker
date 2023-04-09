package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entities.FTUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ActivityDTO {

  private String activityType;
  private List<SelectOptionsDTO> activityTypeList;
  private FTUser ftUser;
  private DateDTO activityDate;
  private TimeDTO timeTaken;
  private BigDecimal distance;
  private Integer calorieCount;
  private String notes;
  private TimeDTO startTime;
  private List<ActivityDetailsDTO> activityDetailsList;

  public ActivityDTO() {
    this.activityDetailsList = new ArrayList<>();
  }

  public String getActivityType() {
    return activityType;
  }

  public void setActivityType(String activityType) {
    this.activityType = activityType;
  }

  public FTUser getFtUser() {
    return ftUser;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public DateDTO getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(DateDTO activityDate) {
    this.activityDate = activityDate;
  }

  public TimeDTO getTimeTaken() {
    return timeTaken;
  }

  public void setTimeTaken(TimeDTO timeTaken) {
    this.timeTaken = timeTaken;
  }

  public BigDecimal getDistance() {
    return distance;
  }

  public void setDistance(BigDecimal distance) {
    this.distance = distance;
  }

  public Integer getCalorieCount() {
    return calorieCount;
  }

  public void setCalorieCount(Integer calorieCount) {
    this.calorieCount = calorieCount;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public TimeDTO getStartTime() {
    return startTime;
  }

  public void setStartTime(TimeDTO startTime) {
    this.startTime = startTime;
  }

  public List<SelectOptionsDTO> getActivityTypeList() {
    return activityTypeList;
  }

  public void setActivityTypeList(
      List<SelectOptionsDTO> activityTypeList) {
    this.activityTypeList = activityTypeList;
  }

  public List<ActivityDetailsDTO> getActivityDetailsList() {
    return activityDetailsList;
  }

  public void setActivityDetailsList(
      List<ActivityDetailsDTO> activityDetailsList) {
    this.activityDetailsList = activityDetailsList;
  }

  @Override
  public String toString() {
    return "ActivityDTO{" +
        "activityType=" + activityType +
        ", ftUser=" + ftUser +
        ", activityDate=" + activityDate +
        ", timeTaken=" + timeTaken +
        ", distance=" + distance +
        ", calorieCount=" + calorieCount +
        ", notes='" + notes + '\'' +
        ", startTime=" + startTime +
        '}';
  }
}
