package com.chhin.fitnesstracker.model;

import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateDTO {

  @Pattern(regexp = "^(?:|0[1-9]|1[0-2]|[1-9])$", message = "Enter a valid date")
  protected String day;
  @Pattern(regexp = "^(?:|0[1-9]|1[0-2]|[1-9])$", message = "Enter a valid date")
  protected String month;
  @Pattern(regexp = "^(?:|\\d{4})$", message = "Enter a valid date")
  protected String year;

  public DateDTO() {
  }

  public DateDTO(LocalDate localDate) {
    this.day = String.valueOf(localDate.getDayOfMonth());
    this.month = String.valueOf(localDate.getMonthValue());
    this.year = String.valueOf(localDate.getYear());
  }

  public DateDTO(String day, String month, String year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getMonth() {
    return month;
  }

  public void setMonth(String month) {
    this.month = month;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public LocalDate toLocalDate() {
    return LocalDate.of(Integer.parseInt(this.getYear()), Integer.parseInt(this.getMonth()),
        Integer.parseInt(this.getDay()));
  }

  public String toDisplayDate() {

    return toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  }
}
