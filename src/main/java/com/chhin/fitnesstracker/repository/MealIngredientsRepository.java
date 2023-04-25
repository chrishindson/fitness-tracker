package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.MealIngredients;
import com.chhin.fitnesstracker.entity.MealTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealIngredientsRepository extends JpaRepository<MealIngredients, Long> {
  @Query("select m from MealIngredients m where m.mealTracking = :mealTracking order by m.foodType.foodName")
  List<MealIngredients> findByMealTrackingByFoodType(@Param("mealTracking") MealTracking mealTracking);


}