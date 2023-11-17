package com.chhin.fitnesstracker.model;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.WeightTracking;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeightTrackingDTO {

  private Long weightTrackingId;
  private FTUser ftUser;
  private BigDecimal weight;
  private BigDecimal skeletalMuscle;
  private BigDecimal fatMass;
  private BigDecimal bodyFat;
  private BigDecimal bodyWater;
  private DateDTO recordedDate;

  public WeightTrackingDTO(WeightTracking weightTracking) {
    this.weightTrackingId = weightTracking.getWeightTrackingId();
    this.ftUser = weightTracking.getFtUser();
    this.weight = weightTracking.getWeight();
    this.skeletalMuscle = weightTracking.getSkeletalMuscle();
    this.fatMass = weightTracking.getFatMass();
    this.bodyFat = weightTracking.getBodyFat();
    this.bodyWater = weightTracking.getBodyWater();
    this.recordedDate = new DateDTO(weightTracking.getRecordedDate());
  }
}
