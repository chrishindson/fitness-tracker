package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.MealType;
import com.chhin.fitnesstracker.model.SelectOptionsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealTypeRepository extends JpaRepository<MealType, Long> {

  @Query("SELECT new com.chhin.fitnesstracker.model.SelectOptionsDTO(a.mealTypeId,a.mealTypeDescription) FROM MealType a ORDER BY a.mealTypeDescription")
  List<SelectOptionsDTO> findAllSelectOptions();
}