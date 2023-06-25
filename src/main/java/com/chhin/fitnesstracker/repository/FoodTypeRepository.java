package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
  @Query("select f from FoodType f where f.foodTypeId = :foodTypeId")
  FoodType findByFoodTypeId(@Param("foodTypeId") Long foodTypeId);

  @Query("SELECT a FROM FoodType a ORDER BY a.foodName")
  List<FoodType> findAllSorted();
}