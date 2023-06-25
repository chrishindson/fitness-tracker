package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.*;
import com.chhin.fitnesstracker.model.*;
import com.chhin.fitnesstracker.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodTrackingService {

  private final FoodTypeRepository foodTypeRepository;
  private final FoodTrackingRepository foodTrackingRepository;
  private final MealTrackingRepository mealTrackingRepository;
  private final MealIngredientsRepository mealIngredientsRepository;
  private final MealTypeRepository mealTypeRepository;
  private final StoredMealRepository storedMealRepository;
  private final StoredMealIngredientsRepository storedMealIngredientsRepository;

  public FoodTrackingService(FoodTypeRepository foodTypeRepository, FoodTrackingRepository foodTrackingRepository, MealTrackingRepository mealTrackingRepository, MealIngredientsRepository mealIngredientsRepository, MealTypeRepository mealTypeRepository,
                             StoredMealRepository storedMealRepository,
                             StoredMealIngredientsRepository storedMealIngredientsRepository) {
    this.foodTypeRepository = foodTypeRepository;
    this.foodTrackingRepository = foodTrackingRepository;
    this.mealTrackingRepository = mealTrackingRepository;
    this.mealIngredientsRepository = mealIngredientsRepository;
    this.mealTypeRepository = mealTypeRepository;
    this.storedMealRepository = storedMealRepository;
    this.storedMealIngredientsRepository = storedMealIngredientsRepository;
  }

  public List<MealType> findAllMealTypes() {
    return mealTypeRepository.findAllSorted();
  }

  public Map<Long, String> findAllMealTypesMap() {
    List<MealType> mealTypeList = findAllMealTypes();
    return mealTypeList.stream().collect(Collectors.toMap(MealType::getMealTypeId, MealType::getMealTypeDescription));
  }

  public List<FoodType> findAllFoodTypes() {
    return foodTypeRepository.findAllSorted();
  }

  public Map<Long, String> findAllFoodTypesMap() {
    List<FoodType> foodTypeList = findAllFoodTypes();
    return foodTypeList.stream().collect(Collectors.toMap(FoodType::getFoodTypeId, FoodType::getFoodName));
  }

  public FoodTracking findAllMealsByRecordedDate(FTUser user, LocalDate recordedDate) {

    return foodTrackingRepository.findFoodTrackingByFoodTrackingDateAndFtUser(user, recordedDate).orElse(null);
  }

  public Page<FoodType> findAllFoodTypes(Pageable pageable) {
    return foodTypeRepository.findAll(pageable);
  }

  public Page<StoredMeal> findAllStoredMeals(Pageable pageable) {
    return storedMealRepository.findAll(pageable);
  }

  public List<MealDetailsDTO> findMealDetailsByUserAndRecordedDate(FTUser user, LocalDate recordedDate) {
    return foodTrackingRepository.findMealTrackingByFoodTrackingDateAndFtUser(user, recordedDate);
  }

  public FoodTrackingDTO createFoodTrackingDTO() {
    FoodTrackingDTO foodTrackingDTO = new FoodTrackingDTO();
    foodTrackingDTO.setMealTypeList(findAllMealTypesMap());
    foodTrackingDTO.setFoodTypeList(findAllFoodTypesMap());
    foodTrackingDTO.setMealIngredientsDTOList(new ArrayList<>());
    return foodTrackingDTO;
  }

  public StoredMealDTO createStoredMealDTO() {
    StoredMealDTO storedMealDTO = new StoredMealDTO();
    storedMealDTO.setFoodTypeList(findAllFoodTypesMap());
    storedMealDTO.setMealIngredientsDTOList(new ArrayList<>());
    return storedMealDTO;

  }


  public void saveFoodType(FoodTypeDTO dto) {

    FoodType foodType;
    if (dto.getFoodTypeId() == null) {
      foodType = new FoodType();
    } else {
      foodType = foodTypeRepository.findByFoodTypeId(dto.getFoodTypeId());
    }
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

  public void saveMeal(FoodTrackingDTO foodTrackingDTO, FTUser ftUser) {
    FoodTracking foodTracking = foodTrackingRepository.findFoodTrackingByFoodTrackingDateAndFtUser(ftUser,
        foodTrackingDTO.getFoodTrackingDate().toLocalDate()).orElse(null);

    if (foodTracking == null) {
      foodTracking = new FoodTracking();
      foodTracking.setFtUser(ftUser);
      foodTracking.setFoodTrackingDate(foodTrackingDTO.getFoodTrackingDate().toLocalDate());
      foodTrackingRepository.save(foodTracking);
    }

    MealType mealType = mealTypeRepository.findByMealTypeId(foodTrackingDTO.getMealType());

    MealTracking mealTracking = mealTrackingRepository.findByFoodTrackingAndMealType(foodTracking, mealType);

    if (mealTracking == null) {
      mealTracking = new MealTracking();
      mealTracking.setFoodTracking(foodTracking);
      mealTracking.setMealType(mealType);
      mealTrackingRepository.save(mealTracking);
    }

    for (MealIngredientsDTO mealIngredientsDTO : foodTrackingDTO.getMealIngredientsDTOList()) {
      FoodType foodType = foodTypeRepository.findByFoodTypeId(Long.parseLong(mealIngredientsDTO.getFoodTypeId()));

      List<MealIngredients> mealIngredientsList = mealIngredientsRepository.findByMealTrackingByFoodType(mealTracking);
      MealIngredients mealIngredients = mealIngredientsList.stream().filter(x -> x.getFoodType().equals(foodType)).findAny().orElse(new MealIngredients());

      mealIngredients.setMealTracking(mealTracking);
      mealIngredients.setFoodType(foodType);
      mealIngredients.setServings(mealIngredientsDTO.getServingSize());
      mealIngredientsRepository.save(mealIngredients);
    }
  }

  public void saveStoredMeal(StoredMealDTO storedMealDTO) {
    StoredMeal storedMeal;

    if (storedMealDTO.getStoredMealId() == null) {
      storedMeal = new StoredMeal();
    } else {
      storedMeal = storedMealRepository.findByStoredMealId(storedMealDTO.getStoredMealId());
    }
    List<FoodType> foodTypeList = foodTypeRepository.findAll();
    storedMeal.setStoredMealName(storedMealDTO.getMealName());
    storedMealRepository.save(storedMeal);
    for (MealIngredientsDTO mealIngredientsDTO : storedMealDTO.getMealIngredientsDTOList()) {
      StoredMealIngredients storedMealIngredients = new StoredMealIngredients();
      storedMealIngredients.setStoredMeal(storedMeal);
      FoodType foodType = foodTypeList.stream().filter(x -> x.getFoodTypeId().equals(Long.parseLong(mealIngredientsDTO.getFoodTypeId()))).findAny().orElseThrow();
      storedMealIngredients.setFoodType(foodType);
      storedMealIngredients.setServings(mealIngredientsDTO.getServingSize());
      storedMealIngredientsRepository.save(storedMealIngredients);
    }

  }

  public FoodTypeDTO findFoodTypeByFoodTypeId(Long foodTypeId) {
    FoodType foodType = foodTypeRepository.findByFoodTypeId(foodTypeId);

    return new FoodTypeDTO(foodType);
  }

  public StoredMealDTO findStoredMealById(Long storedMealId) {
    StoredMealDTO storedMealDTO = new StoredMealDTO();
    StoredMeal storedMeal = storedMealRepository.findByStoredMealId(storedMealId);
    storedMeal.setStoredMealId(storedMeal.getStoredMealId());
    storedMealDTO.setMealName(storedMeal.getStoredMealName());
    List<MealIngredientsDTO> dtoList = new ArrayList<>();
    for (StoredMealIngredients ingredients : storedMeal.getStoredMealIngredientsList()) {
      MealIngredientsDTO ingredientsDTO = new MealIngredientsDTO();
      ingredientsDTO.setFoodTypeId(ingredients.getFoodType().getFoodTypeId().toString());
      ingredientsDTO.setServingSize(ingredients.getServings());
      dtoList.add(ingredientsDTO);
    }
    storedMealDTO.setMealIngredientsDTOList(dtoList);
    storedMealDTO.setFoodTypeList(findAllFoodTypesMap());
    return storedMealDTO;
  }
}
