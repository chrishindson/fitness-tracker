package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;

public class BodyMeasurementDTO {

  private String bodyMeasurementArea;
  private DateDTO measurementDate;
  private BigDecimal measurementSize;

  public String getBodyMeasurementArea() {
    return bodyMeasurementArea;
  }

  public void setBodyMeasurementArea(String bodyMeasurementArea) {
    this.bodyMeasurementArea = bodyMeasurementArea;
  }

  public DateDTO getMeasurementDate() {
    return measurementDate;
  }

  public void setMeasurementDate(DateDTO measurementDate) {
    this.measurementDate = measurementDate;
  }

  public BigDecimal getMeasurementSize() {
    return measurementSize;
  }

  public void setMeasurementSize(BigDecimal measurementSize) {
    this.measurementSize = measurementSize;
  }

  @Override
  public String toString() {
    return "BodyMeasurementDTO{" +
        "bodyMeasurementArea='" + bodyMeasurementArea + '\'' +
        ", measurementDate=" + measurementDate +
        ", measurementSize=" + measurementSize +
        '}';
  }
}
