package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDetailsDTO {
  private Long activityDetailsId;
  private Integer repetitions;
  private BigDecimal weight;
  private Integer numberOfSets;
}
