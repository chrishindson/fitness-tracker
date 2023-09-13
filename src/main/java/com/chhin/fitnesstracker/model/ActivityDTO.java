package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.Activity;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.util.HelperUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityDTO {
  private Long activityId;
  private String activityType;
  private Map<Long, String> activityTypeList;
  private FTUser ftUser;
  private DateDTO activityDate;
  private TimeDTO timeTaken;
  private BigDecimal distance;
  private Integer calorieCount;
  private String notes;
  private TimeDTO startTime;
  private List<ActivityDetailsDTO> activityDetailsList = new ArrayList<>();

  public ActivityDTO(Activity activity) {
    this.activityId = activity.getActivityId();
    this.activityDate = new DateDTO(activity.getActivityDate());
    this.startTime = new TimeDTO(activity.getStartTime());
    this.ftUser = activity.getFtUser();
    this.timeTaken = new TimeDTO(activity.getTimeTaken());
    this.distance = activity.getDistance();
    this.calorieCount = activity.getCalorieCount();
    this.notes = activity.getNotes();
  }
  public LinkedHashMap<Long, String> getSortedMap() {
    return HelperUtils.getSortedMap(this.activityTypeList);
  }
}
