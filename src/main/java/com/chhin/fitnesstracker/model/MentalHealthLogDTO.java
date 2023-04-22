package com.chhin.fitnesstracker.model;

public class MentalHealthLogDTO {
  private DateDTO logDate;
  private String thoughtsEmotions;
  private String potentialTriggers;
  private String intensity;
  private String actualResponse;
  private String futureResponse;
  private String additionalNotes;

  public DateDTO getLogDate() {
    return logDate;
  }

  public void setLogDate(DateDTO logDate) {
    this.logDate = logDate;
  }

  public String getThoughtsEmotions() {
    return thoughtsEmotions;
  }

  public void setThoughtsEmotions(String thoughtsEmotions) {
    this.thoughtsEmotions = thoughtsEmotions;
  }

  public String getPotentialTriggers() {
    return potentialTriggers;
  }

  public void setPotentialTriggers(String potentialTriggers) {
    this.potentialTriggers = potentialTriggers;
  }

  public String getIntensity() {
    return intensity;
  }

  public void setIntensity(String intensity) {
    this.intensity = intensity;
  }

  public String getActualResponse() {
    return actualResponse;
  }

  public void setActualResponse(String actualResponse) {
    this.actualResponse = actualResponse;
  }

  public String getFutureResponse() {
    return futureResponse;
  }

  public void setFutureResponse(String futureResponse) {
    this.futureResponse = futureResponse;
  }

  public String getAdditionalNotes() {
    return additionalNotes;
  }

  public void setAdditionalNotes(String additionalNotes) {
    this.additionalNotes = additionalNotes;
  }
}
