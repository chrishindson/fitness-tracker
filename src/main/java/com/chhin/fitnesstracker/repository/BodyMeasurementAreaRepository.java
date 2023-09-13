package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.BodyMeasurementArea;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementAreaRepository extends JpaRepository<BodyMeasurementArea, Long> {
    @Query("SELECT a FROM BodyMeasurementArea a ORDER BY a.bodyMeasurementAreaDescription")
    List<BodyMeasurementArea> findAllBodyMeasurementAreas();
}