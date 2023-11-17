package com.chhin.fitnesstracker.model;

import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SleepTrackingDTO {
  private TimeDTO lightSleep;
  private TimeDTO remSleep;
  private TimeDTO deepSleep;
  private TimeDTO awakeTime;
  private TimeDTO oxygenUnderNinety;
  private TimeDTO snoringTime;
  private Integer sleepScore;
  private Integer calories;
  private DateDTO recordedDate;
  private TimeDTO sleepStart;
  private TimeDTO sleepEnd;

}
