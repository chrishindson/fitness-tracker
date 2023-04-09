package com.chhin.fitnesstracker.model;

import java.time.LocalDate;
import java.util.List;

public class DailyDashboardDTO {

  private LocalDate dashboardDate;
  private List<ActivityDTO> activityDTOList;
  private List<BodyMeasurementDTO> bodyMeasurementDTOList;
  private List<WeightTrackingDTO> weightTrackingDTOList;
  private ActivitySummaryDTO activitySummaryDTO;
  private SleepTrackingDTO sleepTrackingDTO;

  public LocalDate getDashboardDate() {
    return dashboardDate;
  }

  public void setDashboardDate(LocalDate dashboardDate) {
    this.dashboardDate = dashboardDate;
  }

  public List<ActivityDTO> getActivityDTOList() {
    return activityDTOList;
  }

  public void setActivityDTOList(
      List<ActivityDTO> activityDTOList) {
    this.activityDTOList = activityDTOList;
  }

  public List<BodyMeasurementDTO> getBodyMeasurementDTOList() {
    return bodyMeasurementDTOList;
  }

  public void setBodyMeasurementDTOList(
      List<BodyMeasurementDTO> bodyMeasurementDTOList) {
    this.bodyMeasurementDTOList = bodyMeasurementDTOList;
  }

  public List<WeightTrackingDTO> getWeightTrackingDTOList() {
    return weightTrackingDTOList;
  }

  public void setWeightTrackingDTOList(
      List<WeightTrackingDTO> weightTrackingDTOList) {
    this.weightTrackingDTOList = weightTrackingDTOList;
  }

  public ActivitySummaryDTO getActivitySummaryDTO() {
    return activitySummaryDTO;
  }

  public void setActivitySummaryDTO(ActivitySummaryDTO activitySummaryDTO) {
    this.activitySummaryDTO = activitySummaryDTO;
  }

  public SleepTrackingDTO getSleepTrackingDTO() {
    return sleepTrackingDTO;
  }

  public void setSleepTrackingDTO(SleepTrackingDTO sleepTrackingDTO) {
    this.sleepTrackingDTO = sleepTrackingDTO;
  }
}
