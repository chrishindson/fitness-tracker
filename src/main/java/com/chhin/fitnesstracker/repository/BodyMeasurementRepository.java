package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.BodyMeasurement;
import com.chhin.fitnesstracker.entity.FTUser;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {

  @Query("SELECT a FROM BodyMeasurement a WHERE a.ftUser =:user AND a.measurementDate = :recordedDate")
  List<BodyMeasurement> findBodyMeasurementByFtUserAndMeasurementDate(@Param("user") FTUser user,
                                                                      @Param("recordedDate")
                                                                      LocalDate recordedDate);
}