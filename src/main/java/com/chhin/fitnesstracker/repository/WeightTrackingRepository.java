package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.WeightTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface WeightTrackingRepository extends JpaRepository<WeightTracking, Long> {

  @Query("SELECT a FROM WeightTracking a WHERE a.recordedDate = :recordedDate AND a.ftUser = :user")
  Optional<WeightTracking> findWeightTrackingByRecordedDateAndFtUser(@Param("user") FTUser user,
                                                                     @Param("recordedDate")
                                                                     LocalDate recordedDate);
}