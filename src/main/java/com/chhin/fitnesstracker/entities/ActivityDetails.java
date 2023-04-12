package com.chhin.fitnesstracker.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "activity_details")
public class ActivityDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_details_gen")
  @SequenceGenerator(name = "activity_details_gen", sequenceName = "activity_details_pk_seq", allocationSize = 1)
  @Column(name = "activity_details_id", nullable = false)
  private Long activityDetailsId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "activity_id", nullable = false)
  private Activity activity;

  @Column(name = "repetitions", nullable = false)
  private Integer repetitions;

  @Column(name = "weight", nullable = false, precision = 2)
  private BigDecimal weight;

  @Column(name = "number_of_sets", nullable = false)
  private Integer numberOfSets;


  public Long getActivityDetailsId() {
    return activityDetailsId;
  }

  public void setActivityDetailsId(Long activityDetailsId) {
    this.activityDetailsId = activityDetailsId;
  }

  public Activity getActivity() {
    return activity;
  }

  public void setActivity(Activity activity) {
    this.activity = activity;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivityDetails that = (ActivityDetails) o;
    return Objects.equals(activityDetailsId, that.activityDetailsId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activityDetailsId);
  }
}