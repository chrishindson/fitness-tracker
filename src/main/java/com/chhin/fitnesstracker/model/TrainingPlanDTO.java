package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.util.HelperUtils;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlanDTO {
  private DateDTO startDate;
  private DateDTO endDate;
  private String activityType;
  private boolean logMonday;
  private boolean logTuesday;
  private boolean logWednesday;
  private boolean logThursday;
  private boolean logFriday;
  private boolean logSaturday;
  private boolean logSunday;
  private TimeDTO plannedTime;
  private BigDecimal distance;
  private BigDecimal weight;
  private Integer numberOfSets;
  private Integer repetitions;
  private String notes;
  private Map<Long, String> activityTypeList;

  public LinkedHashMap<Long, String> getSortedMap() {
    return HelperUtils.getSortedMap(this.activityTypeList);
  }
}
