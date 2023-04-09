package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.BodyMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {

}