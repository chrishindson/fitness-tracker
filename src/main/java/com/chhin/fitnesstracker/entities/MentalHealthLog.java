package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "mental_health_log")
public class MentalHealthLog {

  @Id
  @Column(name = "mental_health_log_id")
  @SequenceGenerator(name = "mental_health_log_gen", sequenceName = "mental_health_log_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mental_health_log_gen")
  private Long mentalHealthLogId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;

  @Column(name = "log_date", nullable = false)
  private LocalDate logDate;
  @Column(name = "thoughts_emotions", length = 5000)
  private String thoughtsEmotions;
  @Column(name = "potential_triggers", length = 5000)
  private String potentialTriggers;
  @Column(name = "intensity", length = 5000)
  private String intensity;
  @Column(name = "actual_response", length = 5000)
  private String actualResponse;
  @Column(name = "future_response", length = 5000)
  private String futureResponse;
  @Column(name = "additional_notes", length = 5000)
  private String additionalNotes;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;

  public Long getMentalHealthLogId() {
    return mentalHealthLogId;
  }

  public void setMentalHealthLogId(Long mentalHealthLogId) {
    this.mentalHealthLogId = mentalHealthLogId;
  }

  public FTUser getFtUser() {
    return ftUser;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public LocalDate getLogDate() {
    return logDate;
  }

  public void setLogDate(LocalDate logDate) {
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

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MentalHealthLog that = (MentalHealthLog) o;
    return Objects.equals(mentalHealthLogId, that.mentalHealthLogId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mentalHealthLogId);
  }

  @Override
  public String toString() {
    return "MentalHealthLog{" +
        "mentalHealthLogId=" + mentalHealthLogId +
        ", ftUser=" + ftUser +
        ", logDate=" + logDate +
        ", thoughtsEmotions='" + thoughtsEmotions + '\'' +
        ", potentialTriggers='" + potentialTriggers + '\'' +
        ", intensity='" + intensity + '\'' +
        ", actualResponse='" + actualResponse + '\'' +
        ", futureResponse='" + futureResponse + '\'' +
        ", additionalNotes='" + additionalNotes + '\'' +
        ", createdDate=" + createdDate +
        '}';
  }
}
