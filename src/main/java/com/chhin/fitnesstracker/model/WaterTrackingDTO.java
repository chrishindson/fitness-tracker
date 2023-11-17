package com.chhin.fitnesstracker.model;

import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WaterTrackingDTO {
  private DateDTO waterTrackingDate;
}
