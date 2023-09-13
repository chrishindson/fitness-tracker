package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Entity
@Table(name = "body_measurement")
public class BodyMeasurement {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_measurement_gen")
  @SequenceGenerator(name = "body_measurement_gen", sequenceName = "body_measurement_seq", allocationSize = 1)
  @Column(name = "body_measurement_id", nullable = false)
  private Long bodyMeasurementId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "body_measurement_area_id", nullable = false)
  private BodyMeasurementArea bodyMeasurementArea;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;
  @Column(name = "measurement_date", nullable = false)
  private LocalDate measurementDate;

  @Column(name = "measurement_size", nullable = false, precision = 2)
  private BigDecimal measurementSize;

  public void setBodyMeasurementId(Long bodyMeasurementId) {
    this.bodyMeasurementId = bodyMeasurementId;
  }

  public void setBodyMeasurementArea(
      BodyMeasurementArea bodyMeasurementArea) {
    this.bodyMeasurementArea = bodyMeasurementArea;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public void setMeasurementDate(LocalDate measurementDate) {
    this.measurementDate = measurementDate;
  }

  public void setMeasurementSize(BigDecimal measurementSize) {
    this.measurementSize = measurementSize;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BodyMeasurement that = (BodyMeasurement) o;
    return Objects.equals(bodyMeasurementId, that.bodyMeasurementId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bodyMeasurementId);
  }
}