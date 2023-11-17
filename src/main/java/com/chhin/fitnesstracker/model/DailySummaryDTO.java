package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailySummaryDTO {

  private LocalDate summaryDate;

  private ActivitySummaryDTO activitySummaryDTO;
  private WeightTracking weightTracking;
  private SleepTracking sleepTracking;
  private List<BodyMeasurement> bodyMeasurementList;
  private FoodTracking foodTracking;
  private WaterTracking waterTracking;
  private List<TrainingPlan> trainingPlanList;

  public String getDateStringLink(long diff) {
    return this.summaryDate.plusDays(diff).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
  }

  public String getDateString(long diff) {
    return this.summaryDate.plusDays(diff).format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
  }
}
