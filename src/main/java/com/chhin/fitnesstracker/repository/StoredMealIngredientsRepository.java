package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.StoredMealIngredients;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoredMealIngredientsRepository extends JpaRepository<StoredMealIngredients, Long> {
}