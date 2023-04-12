package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.MealTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealTrackingRepository extends JpaRepository<MealTracking, Long> {

}