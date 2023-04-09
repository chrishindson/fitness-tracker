package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Integer> {
}