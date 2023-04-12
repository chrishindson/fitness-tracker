package com.chhin.fitnesstracker.model;

import jakarta.validation.constraints.Pattern;

import java.time.LocalTime;

public class TimeDTO {

  @Pattern(regexp = "^(?:|0[1-9]|1[0-9]|[1-9])$", message = "Enter a valid time")
  protected String hours;
  @Pattern(regexp = "^(?:|0[1-9]|1[0-5]|[1-9])$", message = "Enter a valid time")
  protected String minutes;
  @Pattern(regexp = "^(?:|0[1-9]|1[0-5]|[1-9])$", message = "Enter a valid time")
  protected String seconds;

  public TimeDTO() {
  }

  public TimeDTO(String hours, String minutes, String seconds) {
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public TimeDTO(LocalTime localTime) {
    this.hours = String.valueOf(localTime.getHour());
    this.minutes = String.valueOf(localTime.getMinute());
    this.seconds = String.valueOf(localTime.getSecond());
  }

  public String getHours() {
    return hours;
  }

  public void setHours(String hours) {
    this.hours = hours;
  }

  public String getMinutes() {
    return minutes;
  }

  public void setMinutes(String minutes) {
    this.minutes = minutes;
  }

  public String getSeconds() {
    return seconds;
  }

  public void setSeconds(String seconds) {
    this.seconds = seconds;
  }

  public LocalTime toLocalTime() {
    return LocalTime.of(Integer.parseInt(this.getHours()), Integer.parseInt(this.getMinutes()),
        Integer.parseInt(this.getSeconds())
    );
  }
}
