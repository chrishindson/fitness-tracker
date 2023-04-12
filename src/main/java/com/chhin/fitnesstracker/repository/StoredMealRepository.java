package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.StoredMeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredMealRepository extends JpaRepository<StoredMeal, Long> {
}