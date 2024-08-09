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
@Table(name = "body_measurement")
public class BodyMeasurement {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_measurement_gen")
  @SequenceGenerator(
      name = "body_measurement_gen",
      sequenceName = "body_measurement_pk_seq",
      allocationSize = 1)
  @Column(name = "body_measurement_id", nullable = false)
  private Long bodyMeasurementId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "body_measurement_area_id", nullable = false)
  private BodyMeasurementArea bodyMeasurementArea;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;

  @Column(name = "measurement_date", nullable = false)
  private LocalDate measurementDate;

  @Column(name = "measurement_size", nullable = false, precision = 2)
  private BigDecimal measurementSize;
}
