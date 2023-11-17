package com.chhin.fitnesstracker.model;

import jakarta.validation.constraints.Pattern;
import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeDTO {

  @Pattern(regexp = "^(?:|0[1-9]|1[0-9]|[1-9])$", message = "Enter a valid time")
  protected String hours;

  @Pattern(regexp = "^(?:|0[1-9]|1[0-5]|[1-9])$", message = "Enter a valid time")
  protected String minutes;

  @Pattern(regexp = "^(?:|0[1-9]|1[0-5]|[1-9])$", message = "Enter a valid time")
  protected String seconds;

  public TimeDTO(LocalTime localTime) {
    this.hours = String.valueOf(localTime.getHour());
    this.minutes = String.valueOf(localTime.getMinute());
    this.seconds = String.valueOf(localTime.getSecond());
  }

  public LocalTime toLocalTime() {
    try {
      return LocalTime.of(
          Integer.parseInt(this.getHours()),
          Integer.parseInt(this.getMinutes()),
          Integer.parseInt(this.getSeconds()));
    } catch (Exception e) {
      return null;
    }
  }
}
