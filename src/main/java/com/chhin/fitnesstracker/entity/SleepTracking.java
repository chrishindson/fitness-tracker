package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "sleep_tracking")
public class SleepTracking {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sleep_tracking_gen")
  @SequenceGenerator(
      name = "sleep_tracking_gen",
      sequenceName = "sleep_tracking_pk_seq",
      allocationSize = 1)
  @Column(name = "sleep_tracking_id", nullable = false)
  private Long sleepTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;

  @Column(name = "recorded_date", nullable = false)
  private LocalDate recordedDate;

  @Column(name = "sleep_start", nullable = false)
  private LocalDateTime sleepStart;

  @Column(name = "sleep_end", nullable = false)
  private LocalDateTime sleepEnd;

  @Column(name = "light_sleep", nullable = false)
  private LocalTime lightSleep;

  @Column(name = "rem_sleep", nullable = false)
  private LocalTime remSleep;

  @Column(name = "deep_sleep", nullable = false)
  private LocalTime deepSleep;

  @Column(name = "awake_time", nullable = false)
  private LocalTime awakeTime;

  @Column(name = "oxygen_under_ninety", nullable = false)
  private LocalTime oxygenUnderNinety;

  @Column(name = "snoring_time", nullable = false)
  private LocalTime snoringTime;

  @Column(name = "sleep_score", nullable = false)
  private Integer sleepScore;

  @Column(name = "calories", nullable = false)
  private Integer calories;

  public LocalTime getTotalSleepTime() {
    Duration between = Duration.between(this.sleepStart, this.sleepEnd);
    return LocalTime.MIDNIGHT.plus(between);
  }
}
