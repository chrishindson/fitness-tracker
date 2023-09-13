package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "activity")
public class Activity {

  @Id
  @SequenceGenerator(name = "activity_gen", sequenceName = "activity_pk_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_gen")
  @Column(name = "activity_id")
  private Long activityId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "activity_type_id")
  private ActivityType activityType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;

  @Column(name = "activity_date", nullable = false)
  private LocalDate activityDate;

  @Column(name = "time_taken", nullable = false)
  private LocalTime timeTaken;

  @Column(name = "distance", precision = 2)
  private BigDecimal distance;

  @Column(name = "calorie_count", nullable = false, precision = 2)
  private Integer calorieCount;

  @Column(name = "notes", length = 500)
  private String notes;
  @Column(name = "start_time", nullable = false)
  private LocalTime startTime;

  @OneToMany
  @JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
  @ToString.Exclude
  private List<ActivityDetails> activityDetails;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Activity activity = (Activity) o;
    return Objects.equals(activityId, activity.activityId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(activityId);
  }
}