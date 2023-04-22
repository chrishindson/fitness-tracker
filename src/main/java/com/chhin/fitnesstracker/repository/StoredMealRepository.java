package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.StoredMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StoredMealRepository extends JpaRepository<StoredMeal, Long> {
  @Query("select s from StoredMeal s where s.storedMealId = :storedMealId")
  StoredMeal findByStoredMealId(@Param("storedMealId") Long storedMealId);

  @Query("select s from StoredMeal s where s.storedMealName = :storedMealName")
  Optional<StoredMeal> findByStoredMealName(@Param("storedMealName") String storedMealName);
}