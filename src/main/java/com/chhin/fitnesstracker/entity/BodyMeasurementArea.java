package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "body_measurement_area")
public class BodyMeasurementArea {

  @Id
  @SequenceGenerator(
      name = "body_measurement_area_gen",
      sequenceName = "body_measurement_area_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "body_measurement_area_gen")
  @Column(name = "body_measurement_area_id")
  private Long bodyMeasurementAreaId;

  @Column(name = "body_measurement_area_description", nullable = false, unique = true, length = 100)
  private String bodyMeasurementAreaDescription;
}
