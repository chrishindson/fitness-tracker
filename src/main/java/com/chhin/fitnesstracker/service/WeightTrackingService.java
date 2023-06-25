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
    WeightTracking weightTracking = new WeightTracking();
    weightTracking.setWeight(dto.getWeight());
    weightTracking.setBodyFat(dto.getBodyFat());
    weightTracking.setBodyWater(dto.getBodyWater());
    weightTracking.setRecordedDate(dto.getRecordedDate().toLocalDate());
    weightTracking.setFatMass(dto.getFatMass());
    weightTracking.setSkeletalMuscle(dto.getSkeletalMuscle());
    weightTracking.setFtUser(user);
    weightTrackingRepository.save(weightTracking);

  }
}
