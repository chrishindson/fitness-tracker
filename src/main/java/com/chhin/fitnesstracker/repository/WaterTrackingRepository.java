package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.WaterTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface WaterTrackingRepository extends JpaRepository<WaterTracking, Long> {

  @Query("SELECT a FROM WaterTracking a WHERE a.recordedDate = :recordedDate AND a.ftUser = :user")
  Optional<WaterTracking> findWaterTrackingByRecordedDateAndFtUser(@Param("user") FTUser user,
                                                                   @Param("recordedDate")
                                                                   LocalDate recordedDate);
}