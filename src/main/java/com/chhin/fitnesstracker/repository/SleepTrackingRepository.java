package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.SleepTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SleepTrackingRepository extends JpaRepository<SleepTracking, Integer> {

}