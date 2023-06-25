package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealTypeRepository extends JpaRepository<MealType, Long> {
  @Query("select m from MealType m where m.mealTypeId = :mealTypeId")
  MealType findByMealTypeId(@Param("mealTypeId") Long mealTypeId);

  @Query("SELECT a FROM MealType a ORDER BY a.mealTypeDescription")
  List<MealType> findAllSorted();
}