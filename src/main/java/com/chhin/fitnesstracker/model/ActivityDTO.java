package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.Activity;
import com.chhin.fitnesstracker.entity.FTUser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ActivityDTO {
  private Long activityId;
  private String activityType;
  private Map<Long, String> activityTypeList;
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

  public ActivityDTO(Activity activity) {
    this.activityId = activity.getActivityId();
    this.activityDate = new DateDTO(activity.getActivityDate());
    this.startTime = new TimeDTO(activity.getStartTime());
    this.ftUser = activity.getFtUser();
    this.timeTaken = new TimeDTO(activity.getTimeTaken());
    this.distance = activity.getDistance();
    this.calorieCount = activity.getCalorieCount();
    this.notes = activity.getNotes();
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
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

  public Map<Long, String> getActivityTypeList() {
    return activityTypeList;
  }

  public void setActivityTypeList(
      Map<Long, String> activityTypeList) {
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
