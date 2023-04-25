package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.SleepTracking;

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

  public SleepTrackingDTO() {

  }

  public TimeDTO getLightSleep() {
    return lightSleep;
  }

  public void setLightSleep(TimeDTO lightSleep) {
    this.lightSleep = lightSleep;
  }

  public TimeDTO getRemSleep() {
    return remSleep;
  }

  public void setRemSleep(TimeDTO remSleep) {
    this.remSleep = remSleep;
  }

  public TimeDTO getDeepSleep() {
    return deepSleep;
  }

  public void setDeepSleep(TimeDTO deepSleep) {
    this.deepSleep = deepSleep;
  }

  public TimeDTO getAwakeTime() {
    return awakeTime;
  }

  public void setAwakeTime(TimeDTO awakeTime) {
    this.awakeTime = awakeTime;
  }

  public TimeDTO getOxygenUnderNinety() {
    return oxygenUnderNinety;
  }

  public void setOxygenUnderNinety(TimeDTO oxygenUnderNinety) {
    this.oxygenUnderNinety = oxygenUnderNinety;
  }

  public TimeDTO getSnoringTime() {
    return snoringTime;
  }

  public void setSnoringTime(TimeDTO snoringTime) {
    this.snoringTime = snoringTime;
  }

  public Integer getSleepScore() {
    return sleepScore;
  }

  public void setSleepScore(Integer sleepScore) {
    this.sleepScore = sleepScore;
  }

  public Integer getCalories() {
    return calories;
  }

  public void setCalories(Integer calories) {
    this.calories = calories;
  }

  public DateDTO getRecordedDate() {
    return recordedDate;
  }

  public void setRecordedDate(DateDTO recordedDate) {
    this.recordedDate = recordedDate;
  }

  public TimeDTO getSleepStart() {
    return sleepStart;
  }

  public void setSleepStart(TimeDTO sleepStart) {
    this.sleepStart = sleepStart;
  }

  public TimeDTO getSleepEnd() {
    return sleepEnd;
  }

  public void setSleepEnd(TimeDTO sleepEnd) {
    this.sleepEnd = sleepEnd;
  }

  @Override
  public String toString() {
    return "SleepTrackingDTO{" +
        "recordedDate=" + recordedDate +
        ", lightSleep=" + lightSleep +
        ", deepSleep=" + deepSleep +
        ", sleepScore=" + sleepScore +
        '}';
  }
}
