package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.entities.FoodTracking;
import com.chhin.fitnesstracker.entities.FoodType;
import com.chhin.fitnesstracker.model.FoodTrackingDTO;
import com.chhin.fitnesstracker.model.FoodTypeDTO;
import com.chhin.fitnesstracker.model.MealDetailsDTO;
import com.chhin.fitnesstracker.model.SelectOptionsDTO;
import com.chhin.fitnesstracker.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FoodTrackingService {

  private final FoodTypeRepository foodTypeRepository;
  private final FoodTrackingRepository foodTrackingRepository;
  private final MealTrackingRepository mealTrackingRepository;
  private final MealIngredientsRepository mealIngredientsRepository;
  private final MealTypeRepository mealTypeRepository;

  public FoodTrackingService(FoodTypeRepository foodTypeRepository,
                             FoodTrackingRepository foodTrackingRepository, MealTrackingRepository mealTrackingRepository,
                             MealIngredientsRepository mealIngredientsRepository, MealTypeRepository mealTypeRepository) {
    this.foodTypeRepository = foodTypeRepository;
    this.foodTrackingRepository = foodTrackingRepository;
    this.mealTrackingRepository = mealTrackingRepository;
    this.mealIngredientsRepository = mealIngredientsRepository;
    this.mealTypeRepository = mealTypeRepository;
  }

  public List<SelectOptionsDTO> findAllMealTypes() {
    return mealTypeRepository.findAllSelectOptions();
  }

  public List<SelectOptionsDTO> findAllFoodTypes() {
    return foodTypeRepository.findAllSelectOptions();
  }

  public FoodTracking findAllMealsByRecordedDate(FTUser user, LocalDate recordedDate) {

    return foodTrackingRepository.findFoodTrackingByFoodTrackingDateAndFtUser(user, recordedDate).orElse(null);
  }

  public List<MealDetailsDTO> findMealDetailsByUserAndRecordedDate(FTUser user, LocalDate recordedDate) {
    return foodTrackingRepository.findMealTrackingByFoodTrackingDateAndFtUser(user, recordedDate);
  }

  public FoodTrackingDTO createFoodTrackingDTO() {
    FoodTrackingDTO foodTrackingDTO = new FoodTrackingDTO();
    foodTrackingDTO.setMealTypeList(findAllMealTypes());
    foodTrackingDTO.setFoodTypeList(findAllFoodTypes());

    return foodTrackingDTO;
  }

  public void saveFoodType(FoodTypeDTO dto) {
    FoodType foodType = new FoodType();
    foodType.setFoodName(dto.getFoodName());
    foodType.setServingSize(dto.getServingSize());
    foodType.setCalories(dto.getCalories());
    foodType.setTotalCarbohydrate(dto.getTotalCarbohydrate());
    foodType.setTotalFat(dto.getTotalFat());
    foodType.setProtein(dto.getProtein());
    foodType.setSaturatedFat(dto.getSaturatedFat());
    foodType.setTransFat(dto.getTransFat());
    foodType.setCholesterol(dto.getCholesterol());
    foodType.setSodium(dto.getSodium());
    foodType.setPotassium(dto.getPotassium());
    foodType.setDietaryFibre(dto.getDietaryFibre());
    foodType.setSugars(dto.getSugars());
    foodType.setVitaminA(dto.getVitaminA());
    foodType.setVitaminC(dto.getVitaminC());
    foodType.setCalcium(dto.getCalcium());
    foodType.setIron(dto.getIron());

    foodTypeRepository.save(foodType);
  }
}
