package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.MentalHealthLog;
import com.chhin.fitnesstracker.model.MentalHealthLogDTO;
import com.chhin.fitnesstracker.repository.MentalHealthLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MentalHealthLogService {
  private final MentalHealthLogRepository mentalHealthLogRepository;

  public MentalHealthLogService(MentalHealthLogRepository mentalHealthLogRepository) {
    this.mentalHealthLogRepository = mentalHealthLogRepository;
  }

  public void save(MentalHealthLogDTO dto, FTUser ftUser) {
    MentalHealthLog healthLog = new MentalHealthLog();
    healthLog.setLogDate(dto.getLogDate().toLocalDate());
    healthLog.setThoughtsEmotions(dto.getThoughtsEmotions());
    healthLog.setPotentialTriggers(dto.getPotentialTriggers());
    healthLog.setIntensity(dto.getIntensity());
    healthLog.setActualResponse(dto.getActualResponse());
    healthLog.setFutureResponse(dto.getFutureResponse());
    healthLog.setAdditionalNotes(dto.getAdditionalNotes());
    healthLog.setCreatedDate(LocalDateTime.now());
    healthLog.setFtUser(ftUser);
    mentalHealthLogRepository.save(healthLog);
  }
}
