package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "activity_details")
public class ActivityDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_details_gen")
  @SequenceGenerator(
      name = "activity_details_gen",
      sequenceName = "activity_details_pk_seq",
      allocationSize = 1)
  @Column(name = "activity_details_id", nullable = false)
  private Long activityDetailsId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "activity_id", nullable = false)
  private Activity activity;

  @Column(name = "repetitions", nullable = false)
  private Integer repetitions;

  @Column(name = "weight", nullable = false, precision = 2)
  private BigDecimal weight;

  @Column(name = "number_of_sets", nullable = false)
  private Integer numberOfSets;
}
