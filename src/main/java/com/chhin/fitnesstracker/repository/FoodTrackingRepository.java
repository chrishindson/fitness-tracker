package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.FoodTracking;
import com.chhin.fitnesstracker.model.MealDetailsDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FoodTrackingRepository extends JpaRepository<FoodTracking, Long> {

  @Query("SELECT a FROM FoodTracking a WHERE a.foodTrackingDate = :recordedDate AND a.ftUser = :user")
  Optional<FoodTracking> findFoodTrackingByFoodTrackingDateAndFtUser(@Param("user") FTUser user,
                                                                     @Param("recordedDate")
                                                                     LocalDate recordedDate);

  @Query("SELECT new com.chhin.fitnesstracker.model.MealDetailsDTO(ft.foodTrackingId, ft.foodTrackingDate, mt.mealTrackingId, mt.mealType.mealTypeId, "
      + "mi.foodType.foodTypeId, mi.servings, food.foodName, food.servingSize, food.calories) "
      + "FROM FoodTracking ft, MealTracking mt, MealIngredients mi, FoodType food "
      + "WHERE ft = mt.foodTracking AND mt = mi.mealTracking AND mi.foodType = food " +
      "AND ft.foodTrackingDate = :recordedDate AND ft.ftUser = :user")
  List<MealDetailsDTO> findMealTrackingByFoodTrackingDateAndFtUser(@Param("user") FTUser user,
                                                                   @Param("recordedDate")
                                                                   LocalDate recordedDate);


}