package com.chhin.fitnesstracker.model;

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
public class ActivityMonthlyDTO {

  private LocalDate activityMonth;
  private List<ActivityDiaryDTO> monthlyActivities;

  public ActivityMonthlyDTO(LocalDate localDate) {
    this.activityMonth = localDate;
  }

  public Integer firstDayOfWeek() {
    return activityMonth.withDayOfMonth(1).getDayOfWeek().getValue();
  }

  public List<String> getDaysOfWeek() {
    return List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
  }

  public Integer getLastDiaryPlace() {
    return activityMonth.withDayOfMonth(1).getDayOfWeek().getValue()
        + activityMonth.lengthOfMonth()
        - 1;
  }

  public void setMonthlyActivities(List<ActivityDiaryDTO> monthlyActivities) {
    this.monthlyActivities = monthlyActivities;
  }

  public Integer getActivityCount(Integer dayInput) {
    int day = dayInput - this.firstDayOfWeek();
    if (day < 0) {
      return 0;
    }
    LocalDate ld = activityMonth.withDayOfMonth(day + 1);

    ActivityDiaryDTO dto =
        monthlyActivities.stream()
            .filter(x -> x.getActivityDate().equals(ld))
            .findFirst()
            .orElse(null);

    if (dto == null) {
      return 0;
    }
    return dto.getActivityCount().intValue();
  }

  public String getDiaryDateString(Integer dayInput) {
    int day = dayInput - this.firstDayOfWeek();
    if (day < 0) {
      return null;
    }
    LocalDate ld = activityMonth.withDayOfMonth(day + 1);
    return ld.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
  }
}
