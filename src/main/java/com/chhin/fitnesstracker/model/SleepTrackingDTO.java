package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.SleepTracking;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
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

  public SleepTrackingDTO(SleepTracking sleep) {
    this.awakeTime = new TimeDTO(sleep.getAwakeTime());
    this.lightSleep = new TimeDTO(sleep.getLightSleep());
    this.remSleep = new TimeDTO(sleep.getRemSleep());
    this.deepSleep = new TimeDTO(sleep.getDeepSleep());
  }
  
}
