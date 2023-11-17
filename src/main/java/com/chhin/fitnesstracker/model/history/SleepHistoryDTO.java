package com.chhin.fitnesstracker.model.history;

import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SleepHistoryDTO {
  private LocalTime totalSleep;
  private LocalTime lightSleep;
  private LocalTime remSleep;
  private LocalTime deepSleep;
  private LocalTime awakeTime;
  private Integer sleepScore;
}
