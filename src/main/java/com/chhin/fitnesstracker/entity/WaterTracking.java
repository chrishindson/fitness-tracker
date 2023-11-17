package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "water_tracking")
public class WaterTracking {

  @Id
  @SequenceGenerator(
      name = "water_tracking_gen",
      sequenceName = "water_tracking_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "water_tracking_gen")
  @Column(name = "water_tracking_id", nullable = false)
  private Long waterTrackingId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private FTUser ftUser;

  @Column(name = "water", nullable = false)
  private Integer water;

  @Column(name = "recorded_date", nullable = false)
  private LocalDate recordedDate;
}
