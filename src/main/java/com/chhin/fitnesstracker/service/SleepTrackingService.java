package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.SleepTracking;
import com.chhin.fitnesstracker.model.SleepTrackingDTO;
import com.chhin.fitnesstracker.model.history.SleepHistoryDTO;
import com.chhin.fitnesstracker.repository.SleepTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SleepTrackingService {

  @Autowired
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final SleepTrackingRepository sleepTrackingRepository;

  public SleepTrackingService(NamedParameterJdbcTemplate namedParameterJdbcTemplate, SleepTrackingRepository sleepTrackingRepository) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

  public SleepHistoryDTO getSleepTrackingHistory(FTUser ftUser) {
    Map<String, Object> params = new HashMap<>();
    params.put("userId", ftUser.getUserId());
    String sql = "SELECT avg(sleep_end-sleep_start) as totalSleep, avg(light_sleep) lightSleep, " +
        "avg(rem_sleep) remSleep, avg(deep_sleep) deepSleep, " +
        "avg(awake_time) awakeTime, avg(sleep_score) sleepScore " +
        "FROM sleep_tracking " +
        "WHERE user_id = :userId";

    return namedParameterJdbcTemplate.queryForObject(sql, params, (rs, rowNum) ->
        new SleepHistoryDTO(rs.getTime(1).toLocalTime(),
            rs.getTime(2).toLocalTime(),
            rs.getTime(3).toLocalTime(),
            rs.getTime(4).toLocalTime(),
            rs.getTime(5).toLocalTime(),
            rs.getBigDecimal(6).intValue()));

  }

  public Page<SleepTracking> getSleepTrackingPagination(FTUser ftUser, Pageable pageable) {
    return sleepTrackingRepository.findByFtUserOrderByRecordedDateDesc(ftUser, pageable);
  }
}
