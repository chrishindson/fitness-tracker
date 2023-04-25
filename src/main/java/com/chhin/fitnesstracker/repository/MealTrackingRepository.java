package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FoodTracking;
import com.chhin.fitnesstracker.entity.MealTracking;
import com.chhin.fitnesstracker.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MealTrackingRepository extends JpaRepository<MealTracking, Long> {
  @Query("select m from MealTracking m where m.foodTracking = :foodTracking and m.mealType = :mealType")
  MealTracking findByFoodTrackingAndMealType(@Param("foodTracking") FoodTracking foodTracking, @Param("mealType") MealType mealType);

}