package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.WeightTracking;
import com.chhin.fitnesstracker.model.WeightTrackingDTO;
import com.chhin.fitnesstracker.repository.WeightTrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class WeightTrackingService {

  private final WeightTrackingRepository weightTrackingRepository;

  public WeightTrackingService(WeightTrackingRepository weightTrackingRepository) {
    this.weightTrackingRepository = weightTrackingRepository;
  }

  public void saveWeightDetails(WeightTrackingDTO dto, FTUser user) {
    WeightTracking weightTracking =
        WeightTracking.builder()
            .weight(dto.getWeight())
            .bodyFat(dto.getBodyFat())
            .bodyWater(dto.getBodyWater())
            .recordedDate(dto.getRecordedDate().toLocalDate())
            .fatMass(dto.getFatMass())
            .skeletalMuscle(dto.getSkeletalMuscle())
            .ftUser(user)
            .build();
    weightTrackingRepository.save(weightTracking);
  }
}
