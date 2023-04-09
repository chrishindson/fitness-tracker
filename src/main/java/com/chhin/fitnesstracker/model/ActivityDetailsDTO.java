package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entities.Activity;

import java.math.BigDecimal;

public class ActivityDetailsDTO {

  private Integer activityDetailsId;
  private Activity activity;
  private Integer repetitions;
  private BigDecimal weight;
  private Integer numberOfSets;

  public ActivityDetailsDTO() {
  }

  public ActivityDetailsDTO(Integer activityDetailsId, Activity activity, Integer repetitions,
                            BigDecimal weight, Integer numberOfSets) {
    this.activityDetailsId = activityDetailsId;
    this.activity = activity;
    this.repetitions = repetitions;
    this.weight = weight;
    this.numberOfSets = numberOfSets;
  }

  public Integer getActivityDetailsId() {
    return activityDetailsId;
  }

  public void setActivityDetailsId(Integer activityDetailsId) {
    this.activityDetailsId = activityDetailsId;
  }

  public Activity getActivity() {
    return activity;
  }

  public void setActivity(Activity activity) {
    this.activity = activity;
  }

  public Integer getRepetitions() {
    return repetitions;
  }

  public void setRepetitions(Integer repetitions) {
    this.repetitions = repetitions;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public Integer getNumberOfSets() {
    return numberOfSets;
  }

  public void setNumberOfSets(Integer numberOfSets) {
    this.numberOfSets = numberOfSets;
  }

  @Override
  public String toString() {
    return "ActivityDetailsDTO{" +
        "activityDetailsId=" + activityDetailsId +
        ", activity=" + activity +
        ", repetitions=" + repetitions +
        ", weight=" + weight +
        ", numberOfSets=" + numberOfSets +
        '}';
  }
}
