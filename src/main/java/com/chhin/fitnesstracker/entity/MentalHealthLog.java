package com.chhin.fitnesstracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "mental_health_log")
public class MentalHealthLog {

  @Id
  @Column(name = "mental_health_log_id")
  @SequenceGenerator(
      name = "mental_health_log_gen",
      sequenceName = "mental_health_log_pk_seq",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mental_health_log_gen")
  private Long mentalHealthLogId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private FTUser ftUser;

  @Column(name = "log_date", nullable = false)
  private LocalDate logDate;

  @Column(name = "thoughts_emotions", length = 5000)
  private String thoughtsEmotions;

  @Column(name = "potential_triggers", length = 5000)
  private String potentialTriggers;

  @Column(name = "intensity", length = 5000)
  private String intensity;

  @Column(name = "actual_response", length = 5000)
  private String actualResponse;

  @Column(name = "future_response", length = 5000)
  private String futureResponse;

  @Column(name = "additional_notes", length = 5000)
  private String additionalNotes;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;
}
