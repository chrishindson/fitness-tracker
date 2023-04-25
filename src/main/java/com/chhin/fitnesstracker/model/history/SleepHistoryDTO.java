package com.chhin.fitnesstracker.model.history;

import java.time.LocalTime;

public class SleepHistoryDTO {
  private LocalTime totalSleep;
  private LocalTime lightSleep;
  private LocalTime remSleep;
  private LocalTime deepSleep;
  private LocalTime awakeTime;
  private Integer sleepScore;

  public SleepHistoryDTO() {
  }

  public SleepHistoryDTO(LocalTime totalSleep, LocalTime lightSleep, LocalTime remSleep, LocalTime deepSleep, LocalTime awakeTime, Integer sleepScore) {
    this.totalSleep = totalSleep;
    this.lightSleep = lightSleep;
    this.remSleep = remSleep;
    this.deepSleep = deepSleep;
    this.awakeTime = awakeTime;
    this.sleepScore = sleepScore;
  }

  public LocalTime getTotalSleep() {
    return totalSleep;
  }

  public void setTotalSleep(LocalTime totalSleep) {
    this.totalSleep = totalSleep;
  }

  public LocalTime getLightSleep() {
    return lightSleep;
  }

  public void setLightSleep(LocalTime lightSleep) {
    this.lightSleep = lightSleep;
  }

  public LocalTime getRemSleep() {
    return remSleep;
  }

  public void setRemSleep(LocalTime remSleep) {
    this.remSleep = remSleep;
  }

  public LocalTime getDeepSleep() {
    return deepSleep;
  }

  public void setDeepSleep(LocalTime deepSleep) {
    this.deepSleep = deepSleep;
  }

  public LocalTime getAwakeTime() {
    return awakeTime;
  }

  public void setAwakeTime(LocalTime awakeTime) {
    this.awakeTime = awakeTime;
  }

  public Integer getSleepScore() {
    return sleepScore;
  }

  public void setSleepScore(Integer sleepScore) {
    this.sleepScore = sleepScore;
  }

  @Override
  public String toString() {
    return "SleepHistoryDTO{" +
        "totalSleep=" + totalSleep +
        ", lightSleep=" + lightSleep +
        ", remSleep=" + remSleep +
        ", deepSleep=" + deepSleep +
        ", awakeTime=" + awakeTime +
        ", sleepScore=" + sleepScore +
        '}';
  }
}
