package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.WeightTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeightTrackingRepository extends JpaRepository<WeightTracking, Long> {

}