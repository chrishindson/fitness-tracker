package com.chhin.fitnesstracker.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MentalHealthLogDTO {
  private DateDTO logDate;
  private String thoughtsEmotions;
  private String potentialTriggers;
  private String intensity;
  private String actualResponse;
  private String futureResponse;
  private String additionalNotes;

}
