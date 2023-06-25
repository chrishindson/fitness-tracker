package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DailySummaryDTO {

  private LocalDate summaryDate;

  private ActivitySummaryDTO activitySummaryDTO;
  private WeightTracking weightTracking;
  private SleepTracking sleepTracking;
  private List<BodyMeasurement> bodyMeasurementList;
  private FoodTracking foodTracking;
  private WaterTracking waterTracking;
  private List<TrainingPlan> trainingPlanList;

  public LocalDate getSummaryDate() {
    return summaryDate;
  }

  public void setSummaryDate(LocalDate summaryDate) {
    this.summaryDate = summaryDate;
  }

  public ActivitySummaryDTO getActivitySummaryDTO() {
    return activitySummaryDTO;
  }

  public void setActivitySummaryDTO(ActivitySummaryDTO activitySummaryDTO) {
    this.activitySummaryDTO = activitySummaryDTO;
  }

  public WeightTracking getWeightTracking() {
    return weightTracking;
  }

  public void setWeightTracking(WeightTracking weightTracking) {
    this.weightTracking = weightTracking;
  }

  public SleepTracking getSleepTracking() {
    return sleepTracking;
  }

  public void setSleepTracking(SleepTracking sleepTracking) {
    this.sleepTracking = sleepTracking;
  }

  public List<BodyMeasurement> getBodyMeasurementList() {
    return bodyMeasurementList;
  }

  public void setBodyMeasurementList(List<BodyMeasurement> bodyMeasurementList) {
    this.bodyMeasurementList = bodyMeasurementList;
  }

  public List<TrainingPlan> getTrainingPlanList() {
    return trainingPlanList;
  }

  public void setTrainingPlanList(List<TrainingPlan> trainingPlanList) {
    this.trainingPlanList = trainingPlanList;
  }

  public FoodTracking getFoodTracking() {
    return foodTracking;
  }

  public void setFoodTracking(FoodTracking foodTracking) {
    this.foodTracking = foodTracking;
  }

  public WaterTracking getWaterTracking() {
    return waterTracking;
  }

  public void setWaterTracking(WaterTracking waterTracking) {
    this.waterTracking = waterTracking;
  }

  public String getDateStringLink(long diff) {
    return this.summaryDate.plusDays(diff).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
  }

  public String getDateString(long diff) {
    return this.summaryDate.plusDays(diff).format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
  }
}
