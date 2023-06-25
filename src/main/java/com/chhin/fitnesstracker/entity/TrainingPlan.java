package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "training_plan")
public class TrainingPlan {

  @Id
  @SequenceGenerator(name = "training_plan_gen", sequenceName = "training_plan_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_plan_gen")
  @Column(name = "training_plan_id")
  private Long trainingPlanId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "activity_type_id")
  private ActivityType activityType;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;

  @Column(name = "activity_date", nullable = false)
  private LocalDate activityDate;

  @Column(name = "distance", precision = 2)
  private BigDecimal distance;
  @Column(name = "planned_time", nullable = false)
  private LocalTime plannedTime;

  @Column(name = "repetitions", nullable = false)
  private Integer repetitions;

  @Column(name = "weight", nullable = false, precision = 2)
  private BigDecimal weight;

  @Column(name = "number_of_sets", nullable = false)
  private Integer numberOfSets;

  @Column(name = "notes", length = 500)
  private String notes;

  public Long getTrainingPlanIdId() {
    return trainingPlanId;
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

  public BigDecimal getDistance() {
    return distance;
  }

  public void setDistance(BigDecimal distance) {
    this.distance = distance;
  }

  public LocalDate getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(LocalDate activityDate) {
    this.activityDate = activityDate;
  }

  public Long getTrainingPlanId() {
    return trainingPlanId;
  }

  public void setTrainingPlanId(Long trainingPlanId) {
    this.trainingPlanId = trainingPlanId;
  }

  public LocalTime getPlannedTime() {
    return plannedTime;
  }

  public void setPlannedTime(LocalTime plannedTime) {
    this.plannedTime = plannedTime;
  }

  public Integer getRepetitions() {
    return repetitions;
  }

  public void setRepetitions(Integer repetitions) {
    this.repetitions = repetitions;
  }

  public BigDecimal getWeight() {
    return weight;
  }

  public void setWeight(BigDecimal weight) {
    this.weight = weight;
  }

  public Integer getNumberOfSets() {
    return numberOfSets;
  }

  public void setNumberOfSets(Integer numberOfSets) {
    this.numberOfSets = numberOfSets;
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