package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.BodyMeasurementArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMeasurementAreaRepository extends JpaRepository<BodyMeasurementArea, Long> {

}