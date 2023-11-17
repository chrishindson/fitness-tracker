package com.chhin.fitnesstracker.model;

import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentalHealthLogDTO {
  private DateDTO logDate;
  private String thoughtsEmotions;
  private String potentialTriggers;
  private String intensity;
  private String actualResponse;
  private String futureResponse;
  private String additionalNotes;
}
