package com.chhin.fitnesstracker.model;

import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DateDTO {

  @Pattern(regexp = "^(?:|0[1-9]|1[0-2]|[1-9])$", message = "Enter a valid date")
  protected String day;

  @Pattern(regexp = "^(?:|0[1-9]|1[0-2]|[1-9])$", message = "Enter a valid date")
  protected String month;

  @Pattern(regexp = "^(?:|\\d{4})$", message = "Enter a valid date")
  protected String year;

  public DateDTO(LocalDate localDate) {
    this.day = String.valueOf(localDate.getDayOfMonth());
    this.month = String.valueOf(localDate.getMonthValue());
    this.year = String.valueOf(localDate.getYear());
  }

  public LocalDate toLocalDate() {
    return LocalDate.of(
        Integer.parseInt(this.getYear()),
        Integer.parseInt(this.getMonth()),
        Integer.parseInt(this.getDay()));
  }

  public String toDisplayDate() {

    return toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }
}
