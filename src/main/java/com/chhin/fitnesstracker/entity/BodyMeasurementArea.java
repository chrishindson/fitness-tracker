package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "body_measurement_area")
public class BodyMeasurementArea {

  @Id
  @SequenceGenerator(name = "body_measurement_area_gen", sequenceName = "body_measurement_area_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_measurement_area_gen")
  @Column(name = "body_measurement_area_id")
  private Long bodyMeasurementAreaId;

  @Column(name = "body_measurement_area_description", nullable = false, unique = true, length = 100)
  private String bodyMeasurementAreaDescription;

  public void setBodyMeasurementAreaId(Long bodyMeasurementAreaId) {
    this.bodyMeasurementAreaId = bodyMeasurementAreaId;
  }

  public void setBodyMeasurementAreaDescription(String bodyMeasurementAreaDescription) {
    this.bodyMeasurementAreaDescription = bodyMeasurementAreaDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BodyMeasurementArea that = (BodyMeasurementArea) o;
    return Objects.equals(bodyMeasurementAreaId, that.bodyMeasurementAreaId) && Objects.equals(
        bodyMeasurementAreaDescription, that.bodyMeasurementAreaDescription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bodyMeasurementAreaId, bodyMeasurementAreaDescription);
  }
}
