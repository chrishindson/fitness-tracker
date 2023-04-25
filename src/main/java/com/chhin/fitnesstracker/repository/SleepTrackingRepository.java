package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.SleepTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SleepTrackingRepository extends JpaRepository<SleepTracking, Long> {
  @Query("select s from SleepTracking s where s.ftUser = :ftUser order by s.recordedDate DESC")
  Page<SleepTracking> findByFtUserOrderByRecordedDateDesc(@Param("ftUser") FTUser ftUser, Pageable pageable);

  @Query("SELECT a FROM SleepTracking a WHERE a.ftUser =:user AND a.recordedDate = :recordedDate")
  Optional<SleepTracking> findSleepTrackingByFtUserAndRecordedDate(@Param("user") FTUser user,
                                                                   @Param("recordedDate") LocalDate recordedDate);

//  @Query(value = "SELECT avg(sleep_end-sleep_start) as totalSleep, avg(light_sleep) lightSleep, " +
//      "avg(rem_sleep) remSleep, avg(deep_sleep) deepSleep," +
//      "avg(awake_time) awakeTime, avg(sleep_score) sleepScore " +
//      "FROM sleep_tracking " +
//      "WHERE user_id = :userId", nativeQuery = true)
//  SleepHistoryDTO findSleepTrackingHistoryByUser(@Param("userId") Long userId);
}