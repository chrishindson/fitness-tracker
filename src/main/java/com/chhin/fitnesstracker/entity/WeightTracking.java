package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "weight_tracking")
public class WeightTracking {

  @Id
  @SequenceGenerator(
      name = "weight_tracking_gen",
      sequenceName = "weight_tracking_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weight_tracking_gen")
  @Column(name = "weight_tracking_id", nullable = false)
  private Long weightTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private FTUser ftUser;

  @Column(name = "weight", nullable = false, precision = 2)
  private BigDecimal weight;

  @Column(name = "skeletal_muscle", precision = 2)
  private BigDecimal skeletalMuscle;

  @Column(name = "fat_mass", precision = 2)
  private BigDecimal fatMass;

  @Column(name = "body_fat", precision = 2)
  private BigDecimal bodyFat;

  @Column(name = "body_water", nullable = false, precision = 2)
  private BigDecimal bodyWater;

  @Column(name = "recorded_date", nullable = false)
  private LocalDate recordedDate;
}
