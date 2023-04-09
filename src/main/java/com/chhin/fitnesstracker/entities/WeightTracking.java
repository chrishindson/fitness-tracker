package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "weight_tracking")
public class WeightTracking {

  @Id
  @SequenceGenerator(name = "weight_tracking_gen", sequenceName = "weight_tracking_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weight_tracking_gen")
  @Column(name = "weight_tracking_id", nullable = false)
  private Long weightTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private FTUser ftUser;

  @Column(name = "weight", nullable = false, precision = 2)
  private BigDecimal weight;
  @Column(name = "skeletal_muscle", precision = 2)
  private BigDecimal skeletalMuscle;
  @Column(name = "fat_mass", precision = 2)
  private BigDecimal fatMass;
  @Column(name = "body_fat", precision = 2)
  private BigDecimal bodyFat;
  @Column(name = "body_water", nullable = false, precision = 2)
  private BigDecimal bodyWater;

  @Column(name = "recorded_date", nullable = false)
  private LocalDate recordedDate;

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

  public LocalDate getRecordedDate() {
    return recordedDate;
  }

  public void setRecordedDate(LocalDate recordedDate) {
    this.recordedDate = recordedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WeightTracking that = (WeightTracking) o;
    return Objects.equals(weightTrackingId, that.weightTrackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(weightTrackingId);
  }

  @Override
  public String toString() {
    return "WeightTracking{" +
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