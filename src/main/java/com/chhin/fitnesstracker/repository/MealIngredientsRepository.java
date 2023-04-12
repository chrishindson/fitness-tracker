package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entities.MealIngredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealIngredientsRepository extends JpaRepository<MealIngredients, Long> {


}