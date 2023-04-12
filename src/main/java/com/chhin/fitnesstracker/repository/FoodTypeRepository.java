package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.FoodType;
import com.chhin.fitnesstracker.model.SelectOptionsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Long> {
  @Query("SELECT new com.chhin.fitnesstracker.model.SelectOptionsDTO(a.foodTypeId,a.foodName) FROM FoodType a ORDER BY a.foodName")
  List<SelectOptionsDTO> findAllSelectOptions();
}