package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "activity_type")
public class ActivityType {

  @Id
  @Column(name = "activity_type_id")
  @SequenceGenerator(name = "activity_type_gen", sequenceName = "activity_type_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_type_gen")
  private Long activityTypeId;

  @Column(name = "activity_type_description", nullable = false, unique = true, length = 100)
  private String activityTypeDescription;

  public void setActivityTypeId(Long activityTypeId) {
    this.activityTypeId = activityTypeId;
  }

  public void setActivityTypeDescription(String activityTypeDescription) {
    this.activityTypeDescription = activityTypeDescription;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivityType that = (ActivityType) o;
    return Objects.equals(activityTypeId, that.activityTypeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activityTypeId);
  }

}
