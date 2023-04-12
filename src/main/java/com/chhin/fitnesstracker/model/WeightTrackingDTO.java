package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.entities.WeightTracking;

import java.math.BigDecimal;

public class WeightTrackingDTO {

  private Long weightTrackingId;
  private FTUser ftUser;
  private BigDecimal weight;
  private BigDecimal skeletalMuscle;
  private BigDecimal fatMass;
  private BigDecimal bodyFat;
  private BigDecimal bodyWater;
  private DateDTO recordedDate;

  public WeightTrackingDTO() {
  }

  public WeightTrackingDTO(WeightTracking weightTracking) {
    this.weightTrackingId = weightTracking.getWeightTrackingId();
    this.ftUser = weightTracking.getFtUser();
    this.weight = weightTracking.getWeight();
    this.skeletalMuscle = weightTracking.getSkeletalMuscle();
    this.fatMass = weightTracking.getFatMass();
    this.bodyFat = weightTracking.getBodyFat();
    this.bodyWater = weightTracking.getBodyWater();
    this.recordedDate = new DateDTO(weightTracking.getRecordedDate());
  }

  public Long getWeightTrackingId() {
    return weightTrackingId;
  }

  public void setWeightTrackingId(Long weightTrackingId) {
    this.weightTrackingId = weightTrackingId;
  }

  public FTUser getFtUser() {
    return ftUser;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public BigDecimal getSkeletalMuscle() {
    return skeletalMuscle;
  }

  public void setSkeletalMuscle(BigDecimal skeletalMuscle) {
    this.skeletalMuscle = skeletalMuscle;
  }

  public BigDecimal getFatMass() {
    return fatMass;
  }

  public void setFatMass(BigDecimal fatMass) {
    this.fatMass = fatMass;
  }

  public BigDecimal getBodyFat() {
    return bodyFat;
  }

  public void setBodyFat(BigDecimal bodyFat) {
    this.bodyFat = bodyFat;
  }

  public BigDecimal getBodyWater() {
    return bodyWater;
  }

  public void setBodyWater(BigDecimal bodyWater) {
    this.bodyWater = bodyWater;
  }

  public DateDTO getRecordedDate() {
    return recordedDate;
  }

  public void setRecordedDate(DateDTO recordedDate) {
    this.recordedDate = recordedDate;
  }

  @Override
  public String toString() {
    return "WeightTrackingDTO{" +
        "weightTrackingId=" + weightTrackingId +
        ", ftUser=" + ftUser +
        ", weight=" + weight +
        ", skeletalMuscle=" + skeletalMuscle +
        ", fatMass=" + fatMass +
        ", bodyFat=" + bodyFat +
        ", bodyWater=" + bodyWater +
        ", recordedDate=" + recordedDate +
        '}';
  }
}
