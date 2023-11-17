package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "training_plan")
public class TrainingPlan {

  @Id
  @SequenceGenerator(
      name = "training_plan_gen",
      sequenceName = "training_plan_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_plan_gen")
  @Column(name = "training_plan_id")
  private Long trainingPlanId;

  @ManyToOne(fetch = FetchType.LAZY)
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
}
