package com.chhin.fitnesstracker.model;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActivityDetailsDTO {
  private Long activityDetailsId;
  private Integer repetitions;
  private BigDecimal weight;
  private Integer numberOfSets;

}
