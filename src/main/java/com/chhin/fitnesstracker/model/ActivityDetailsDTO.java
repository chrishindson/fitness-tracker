package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;

public class ActivityDetailsDTO {
  private Long activityDetailsId;
  private Integer repetitions;
  private BigDecimal weight;
  private Integer numberOfSets;

  public ActivityDetailsDTO() {
  }

  public ActivityDetailsDTO(Long activityDetailsId, Integer repetitions,
                            BigDecimal weight, Integer numberOfSets) {
    this.activityDetailsId = activityDetailsId;
    this.repetitions = repetitions;
    this.weight = weight;
    this.numberOfSets = numberOfSets;
  }

  public Long getActivityDetailsId() {
    return activityDetailsId;
  }

  public void setActivityDetailsId(Long activityDetailsId) {
    this.activityDetailsId = activityDetailsId;
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
        ", repetitions=" + repetitions +
        ", weight=" + weight +
        ", numberOfSets=" + numberOfSets +
        '}';
  }
}
