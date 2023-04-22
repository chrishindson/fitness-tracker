package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.entities.SleepTracking;
import com.chhin.fitnesstracker.model.SleepTrackingDTO;
import com.chhin.fitnesstracker.repository.SleepTrackingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class SleepTrackingService {

  private final SleepTrackingRepository sleepTrackingRepository;

  public SleepTrackingService(SleepTrackingRepository sleepTrackingRepository) {
    this.sleepTrackingRepository = sleepTrackingRepository;
  }

  public void saveSleepTracking(SleepTrackingDTO dto, FTUser user) {
    SleepTracking sleepTracking = new SleepTracking();
    sleepTracking.setRecordedDate(dto.getRecordedDate().toLocalDate());
    LocalDateTime sleepStart;
    if (dto.getSleepStart().toLocalTime().isBefore(LocalTime.NOON)) {
      sleepStart = dto.getRecordedDate().toLocalDate().atTime(dto.getSleepStart().toLocalTime());
    } else {
      sleepStart = dto.getRecordedDate().toLocalDate().minusDays(1).atTime(dto.getSleepStart().toLocalTime());
    }
    sleepTracking.setSleepStart(sleepStart);
    sleepTracking.setSleepEnd(dto.getRecordedDate().toLocalDate().atTime(dto.getSleepEnd().toLocalTime()));
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
