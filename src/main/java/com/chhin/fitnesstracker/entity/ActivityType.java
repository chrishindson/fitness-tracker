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
@Table(name = "activity_type")
public class ActivityType {

  @Id
  @Column(name = "activity_type_id")
  @SequenceGenerator(
      name = "activity_type_gen",
      sequenceName = "activity_type_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_type_gen")
  private Long activityTypeId;

  @Column(name = "activity_type_description", nullable = false, unique = true, length = 100)
  private String activityTypeDescription;
}
