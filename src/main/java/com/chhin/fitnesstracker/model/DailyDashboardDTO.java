package com.chhin.fitnesstracker.model;

import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyDashboardDTO {

  private LocalDate dashboardDate;
  private List<ActivityDTO> activityDTOList;
  private List<BodyMeasurementDTO> bodyMeasurementDTOList;
  private List<WeightTrackingDTO> weightTrackingDTOList;
  private ActivitySummaryDTO activitySummaryDTO;
  private SleepTrackingDTO sleepTrackingDTO;
}
