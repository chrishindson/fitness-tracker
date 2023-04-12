package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.entities.SleepTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SleepTrackingRepository extends JpaRepository<SleepTracking, Long> {

  @Query("SELECT a FROM SleepTracking a WHERE a.ftUser =:user AND a.recordedDate = :recordedDate")
  Optional<SleepTracking> findSleepTrackingByFtUserAndRecordedDate(@Param("user") FTUser user,
                                                                   @Param("recordedDate") LocalDate recordedDate);
}