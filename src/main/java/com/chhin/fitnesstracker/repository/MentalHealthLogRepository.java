package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.MentalHealthLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentalHealthLogRepository extends JpaRepository<MentalHealthLog, Long> {
}