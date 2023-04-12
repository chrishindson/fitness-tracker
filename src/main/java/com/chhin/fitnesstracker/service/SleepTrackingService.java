package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.entities.SleepTracking;
import com.chhin.fitnesstracker.model.SleepTrackingDTO;
import com.chhin.fitnesstracker.repository.SleepTrackingRepository;
import org.springframework.stereotype.Service;

@Service
public class SleepTrackingService {

  private final SleepTrackingRepository sleepTrackingRepository;

  public SleepTrackingService(SleepTrackingRepository sleepTrackingRepository) {
    this.sleepTrackingRepository = sleepTrackingRepository;
  }

  public void saveSleepTracking(SleepTrackingDTO dto, FTUser user) {
    SleepTracking sleepTracking = new SleepTracking();
    sleepTracking.setRecordedDate(dto.getRecordedDate().toLocalDate());
    sleepTracking.setSleepStart(dto.getSleepStart().toLocalTime());
    sleepTracking.setSleepEnd(dto.getSleepEnd().toLocalTime());
    sleepTracking.setAwakeTime(dto.getAwakeTime().toLocalTime());
    sleepTracking.setRemSleep(dto.getRemSleep().toLocalTime());
    sleepTracking.setLightSleep(dto.getLightSleep().toLocalTime());
    sleepTracking.setDeepSleep(dto.getDeepSleep().toLocalTime());
    sleepTracking.setOxygenUnderNinety(dto.getOxygenUnderNinety().toLocalTime());
    sleepTracking.setSnoringTime(dto.getSnoringTime().toLocalTime());
    sleepTracking.setSleepScore(dto.getSleepScore());
    sleepTracking.setCalories(dto.getCalories());
    sleepTracking.setFtUser(user);
    sleepTrackingRepository.save(sleepTracking);
  }
}
