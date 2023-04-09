package com.chhin.fitnesstracker.model;

import java.time.LocalDate;
import java.util.List;

public class ActivityMonthlyDTO {
  private List<ActivityCalendarDTO> monthlyActivities;


  public Integer daysInMonth() {
    return LocalDate.now().lengthOfMonth();
  }

  public Integer firstDayOfWeek() {
    return LocalDate.now().withDayOfMonth(1).getDayOfWeek().getValue();
  }

  public List<String> getDaysOfWeek() {
    return List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
  }

  public Integer getLastCalendarPlace() {
    return LocalDate.now().withDayOfMonth(1).getDayOfWeek().getValue() + LocalDate.now().lengthOfMonth() - 1;
  }

  public List<ActivityCalendarDTO> getMonthlyActivities() {
    return monthlyActivities;
  }

  public void setMonthlyActivities(List<ActivityCalendarDTO> monthlyActivities) {
    this.monthlyActivities = monthlyActivities;
  }

  public Integer getActivityCount(Integer dayInput) {
    int day = dayInput - this.firstDayOfWeek();
    if (day < 0) {
      return 0;
    }
    LocalDate ld = LocalDate.now().withDayOfMonth(day + 1);

    ActivityCalendarDTO dto = monthlyActivities.stream().filter(x -> x.getActivityDate().equals(ld)).findFirst().orElse(null);

    if (dto == null) {
      return 0;
    }
    return dto.getActivityCount().intValue();
  }
}
