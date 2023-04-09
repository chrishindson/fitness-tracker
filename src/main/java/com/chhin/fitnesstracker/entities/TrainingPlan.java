package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "training_plan")
public class TrainingPlan {

  @Id
  @SequenceGenerator(name = "training_plan_gen", sequenceName = "training_plan_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_plan_gen")
  @Column(name = "training_plan_id")
  private Integer trainingPlanId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "activity_type_id")
  private ActivityType activityType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;

  @Column(name = "activity_date", nullable = false)
  private LocalDate activityDate;

  @Column(name = "notes", length = 500)
  private String notes;

  public Integer getTrainingPlanIdId() {
    return trainingPlanId;
  }

  public void setTrainingPlanId(Integer trainingPlanId) {
    this.trainingPlanId = trainingPlanId;
  }

  public ActivityType getActivityType() {
    return activityType;
  }

  public void setActivityType(ActivityType activityType) {
    this.activityType = activityType;
  }

  public FTUser getFtUser() {
    return ftUser;
  }

  public void setFtUser(FTUser ftUser) {
    this.ftUser = ftUser;
  }

  public LocalDate getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(LocalDate activityDate) {
    this.activityDate = activityDate;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TrainingPlan trainingPlan = (TrainingPlan) o;
    return Objects.equals(trainingPlanId, trainingPlan.trainingPlanId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trainingPlanId);
  }
}