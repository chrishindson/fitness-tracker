package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Entity
@Table(name = "water_tracking")
public class WaterTracking {

  @Id
  @SequenceGenerator(name = "water_tracking_gen", sequenceName = "water_tracking_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "water_tracking_gen")
  @Column(name = "water_tracking_id", nullable = false)
  private Long waterTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private FTUser ftUser;

  @Column(name = "water", nullable = false)
  private Integer water;

  @Column(name = "recorded_date", nullable = false)
  private LocalDate recordedDate;

  public void setWaterTrackingId(Long waterTrackingId) {
    this.waterTrackingId = waterTrackingId;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public void setWater(Integer water) {
    this.water = water;
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
    WaterTracking that = (WaterTracking) o;
    return Objects.equals(waterTrackingId, that.waterTrackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(waterTrackingId);
  }

  @Override
  public String toString() {
    return "WaterTracking{" +
        "waterTrackingId=" + waterTrackingId +
        ", ftUser=" + ftUser +
        ", water=" + water +
        ", recordedDate=" + recordedDate +
        '}';
  }
}