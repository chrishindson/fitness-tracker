package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "sleep_tracking")
public class SleepTracking {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sleep_tracking_gen")
  @SequenceGenerator(name = "sleep_tracking_gen", sequenceName = "sleep_tracking_pk_seq", allocationSize = 1)
  @Column(name = "sleep_tracking_id", nullable = false)
  private Long sleepTrackingId;

  @ManyToOne(fetch = FetchType.EAGER)
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

  public void setSleepTrackingId(Long sleepTrackingId) {
    this.sleepTrackingId = sleepTrackingId;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public void setLightSleep(LocalTime lightSleep) {
    this.lightSleep = lightSleep;
  }

  public void setRemSleep(LocalTime remSleep) {
    this.remSleep = remSleep;
  }

  public void setDeepSleep(LocalTime deepSleep) {
    this.deepSleep = deepSleep;
  }

  public void setAwakeTime(LocalTime awakeTime) {
    this.awakeTime = awakeTime;
  }

  public void setOxygenUnderNinety(LocalTime oxygenUnderNinety) {
    this.oxygenUnderNinety = oxygenUnderNinety;
  }

  public void setSnoringTime(LocalTime snoringTime) {
    this.snoringTime = snoringTime;
  }

  public void setSleepScore(Integer sleepScore) {
    this.sleepScore = sleepScore;
  }

  public void setCalories(Integer calories) {
    this.calories = calories;
  }

  public void setRecordedDate(LocalDate recordedDate) {
    this.recordedDate = recordedDate;
  }

  public void setSleepStart(LocalDateTime sleepStart) {
    this.sleepStart = sleepStart;
  }

  public void setSleepEnd(LocalDateTime sleepEnd) {
    this.sleepEnd = sleepEnd;
  }

  public LocalTime getTotalSleepTime() {
    Duration between = Duration.between(this.sleepStart, this.sleepEnd);
    return LocalTime.MIDNIGHT.plus(between);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SleepTracking that = (SleepTracking) o;
    return Objects.equals(sleepTrackingId, that.sleepTrackingId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sleepTrackingId);
  }

  @Override
  public String toString() {
    return "SleepTracking{" +
        "sleepTrackingId=" + sleepTrackingId +
        ", ftUser=" + ftUser +
        ", lightSleep=" + lightSleep +
        ", deepSleep=" + deepSleep +
        ", oxygenUnderNinety=" + oxygenUnderNinety +
        ", snoringTime=" + snoringTime +
        ", sleepScore=" + sleepScore +
        ", calories=" + calories +
        ", recordedDate=" + recordedDate +
        ", sleepStart=" + sleepStart +
        ", sleepEnd=" + sleepEnd +
        '}';
  }
}