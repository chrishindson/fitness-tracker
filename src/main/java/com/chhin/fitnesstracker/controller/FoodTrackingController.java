package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.entities.FoodType;
import com.chhin.fitnesstracker.entities.StoredMeal;
import com.chhin.fitnesstracker.model.*;
import com.chhin.fitnesstracker.service.FoodTrackingService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import com.chhin.fitnesstracker.validation.FoodTypeValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.chhin.fitnesstracker.util.Constants.FOOD_TRACKING_MAPPING;

@Controller
@SessionAttributes({"foodTrackingDTO", "storedMealDTO"})
public class FoodTrackingController extends AbstractController {
  public static final String ADD_FOOD_DETAILS = "food-tracking/add-food-details";
  public static final String FOOD_TRACKING_HOME_MAPPING = "/food-tracking/home";
  public static final String ADD_MEAL_TYPE_MAPPING = "/food-tracking/add-meal-type";
  public static final String ADD_MEAL_CHECK_MAPPING = "/food-tracking/add-meal-check";
  public static final String ADD_MEAL_TYPE_VIEW = "food-tracking/add-meal-type";
  public static final String ADD_MEAL_INGREDIENTS_MAPPING = "/food-tracking/add-meal-ingredients";
  public static final String ADD_FOOD_TYPE_MAPPING = "/food-tracking/add-food-type";
  public static final String ADD_FOOD_TYPE_VIEW = "food-tracking/food-types/add-food-type";
  public static final String ADD_FOOD_DETAILS_MAPPING = "/food-tracking/add-food-details";
  public static final String ADD_MEAL_INGREDIENTS_VIEW = "food-tracking/add-meal-ingredients";
  public static final String STORED_MEAL_DTO = "storedMealDTO";
  public static final String ADD_STORED_MEAL_INGREDIENTS_MAPPING = "/food-tracking/add-stored-meal-ingredients";
  public static final String ADD_STORED_MEAL_INGREDIENTS_VIEW = "food-tracking/add-stored-meal-ingredients";
  public static final String MEAL_INGREDIENTS_DTO = "mealIngredientsDTO";
  private static final String FOOD_TYPE_DTO = "foodTypeDTO";
  private static final String FOOD_TRACKING_DTO = "foodTrackingDTO";
  private static final String ADD_MEAL_CHECK_VIEW = "food-tracking/add-meal-check";
  private static final String ADD_MEAL_DETAILS_MAPPING = "/food-tracking/add-meal-details";
  private static final String ADD_MEAL_DETAILS_VIEW = "food-tracking/add-meal-details";
  private static final String MANAGE_FOOD_TYPES_MAPPING = "/food-tracking/manage-food-types";
  private static final String MANAGE_FOOD_TYPES_VIEW = "food-tracking/food-types/manage-food-types";
  private static final String MANAGE_STORED_MEALS_MAPPING = "/food-tracking/manage-stored-meals";
  private static final String MANAGE_STORED_MEALS_VIEW = "food-tracking/stored-meals/manage-stored-meals";
  private static final String CREATE_MEAL_MAPPING = "/food-tracking/create-stored-meal";
  private static final String CREATE_MEAL_VIEW = "food-tracking/stored-meals/create-stored-meal";
  private final LoggedInUserService loggedInUserService;
  private final FoodTrackingService foodTrackingService;
  private final FoodTypeValidator foodTypeValidator;


  public FoodTrackingController(LoggedInUserService loggedInUserService, FoodTrackingService foodTrackingService, FoodTypeValidator foodTypeValidator) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.foodTrackingService = foodTrackingService;
    this.foodTypeValidator = foodTypeValidator;
  }

  @GetMapping(FOOD_TRACKING_HOME_MAPPING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Food tracking home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(FOOD_TRACKING_MAPPING);
  }

  @GetMapping(ADD_FOOD_DETAILS_MAPPING)
  public String viewAddFoodDetails(Model model, HttpServletRequest request) {

    if (!model.containsAttribute(FOOD_TRACKING_DTO) || !model.containsAttribute(ATTR_BINDING_RESULT + FOOD_TRACKING_DTO)) {
      model.addAttribute(FOOD_TRACKING_DTO, foodTrackingService.createFoodTrackingDTO());
    }
    titleString = "Add food details";
    getBreadcrumbs(titleString, model, request);
    return ADD_FOOD_DETAILS;

  }

  @GetMapping(MANAGE_FOOD_TYPES_MAPPING)
  public String viewManageFoodTypes(
      @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
      @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
      Model model,
      HttpServletRequest request) {

    Pageable pageable = PageRequest.of(page - 1, size);
    Page<FoodType> foodTypes = foodTrackingService.findAllFoodTypes(pageable);
    model.addAttribute("foodTypes", foodTypes);

    titleString = "Manage food type";
    getBreadcrumbs(titleString, model, request);
    return MANAGE_FOOD_TYPES_VIEW;

  }

  @GetMapping("/food-tracking/manage-food-types/edit")
  public String viewManageFoodTypes(
      @RequestParam(name = "foodTypeId") Long foodTypeId,
      Model model,
      HttpServletRequest request) {

    FoodTypeDTO foodTypeDTO = foodTrackingService.findFoodTypeByFoodTypeId(foodTypeId);
    model.addAttribute(FOOD_TYPE_DTO, foodTypeDTO);
    titleString = "Edit " + foodTypeDTO.getFoodName().toLowerCase();
    getBreadcrumbs(titleString, model, request);
    return "food-tracking/food-types/edit-food-type";

  }

  @PostMapping("/food-tracking/manage-food-types/edit")
  public String viewManageFoodTypesPost(
      @Validated @ModelAttribute(FOOD_TYPE_DTO) FoodTypeDTO foodTypeDTO,
      final BindingResult bindingResult) {

    foodTrackingService.saveFoodType(foodTypeDTO);
    return REDIRECT + MANAGE_FOOD_TYPES_MAPPING;

  }

  @GetMapping("/food-tracking/manage-stored-meals/edit")
  public String viewManageStoredMeals(
      @RequestParam(name = "storedMealId") Long storedMealId,
      Model model,
      HttpServletRequest request) {

    StoredMealDTO storedMealDTO = foodTrackingService.findStoredMealById(storedMealId);
    model.addAttribute(STORED_MEAL_DTO, storedMealDTO);
    titleString = "Edit " + storedMealDTO.getMealName().toLowerCase();
    getBreadcrumbs(titleString, model, request);
    return "food-tracking/stored-meals/edit-stored-meal";

  }

  @PostMapping("/food-tracking/manage-stored-meals/edit")
  public String viewManageStoredMealsPost(
      @Validated @ModelAttribute(STORED_MEAL_DTO) StoredMealDTO storedMealDTO,
      final BindingResult bindingResult) {

    foodTrackingService.saveStoredMeal(storedMealDTO);
    return REDIRECT + MANAGE_STORED_MEALS_MAPPING;

  }

  @GetMapping(MANAGE_STORED_MEALS_MAPPING)
  public String viewManageStoredMeals(
      @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
      @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
      Model model,
      HttpServletRequest request) {

    Pageable pageable = PageRequest.of(page - 1, size);
    Page<StoredMeal> storedMeals = foodTrackingService.findAllStoredMeals(pageable);
    model.addAttribute("storedMeals", storedMeals);

    titleString = "Manage stored meals";
    getBreadcrumbs(titleString, model, request);
    return MANAGE_STORED_MEALS_VIEW;

  }

  @PostMapping(ADD_FOOD_DETAILS_MAPPING)
  public String viewAddFoodDetailsPost(
      @Validated @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {


    redirectAttributes.addFlashAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    return REDIRECT + ADD_MEAL_TYPE_MAPPING;

  }

  @GetMapping(ADD_MEAL_TYPE_MAPPING)
  public String viewMealTypeDetails(
      @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      Model model,
      HttpServletRequest request) {
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    List<MealDetailsDTO> mealDetailsDTOList = foodTrackingService.findMealDetailsByUserAndRecordedDate(ftUser, foodTrackingDTO.getFoodTrackingDate().toLocalDate());
    foodTrackingDTO.setMealDetailsDTOList(mealDetailsDTOList);
    model.addAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    titleString = "Add meal type";
    getBreadcrumbs(titleString, model, request);
    return ADD_MEAL_TYPE_VIEW;

  }

  @PostMapping(ADD_MEAL_TYPE_MAPPING)
  public String viewAddMealTypePost(
      @Validated @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {


    redirectAttributes.addFlashAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    return REDIRECT + ADD_MEAL_DETAILS_MAPPING;

  }

  @GetMapping(ADD_MEAL_DETAILS_MAPPING)
  public String viewAddMealDetails(
      @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      Model model,
      HttpServletRequest request) {

    titleString = "Add meal details";
    getBreadcrumbs(titleString, model, request);
    return ADD_MEAL_DETAILS_VIEW;

  }

  @PostMapping(ADD_MEAL_DETAILS_MAPPING)
  public String viewAddMealDetailsPost(
      @Validated @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {


    redirectAttributes.addFlashAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    return REDIRECT + ADD_MEAL_CHECK_MAPPING;

  }

  @GetMapping(ADD_MEAL_INGREDIENTS_MAPPING)
  public String viewAddMealIngredients(
      @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      Model model,
      HttpServletRequest request) {

    titleString = "Add meal ingredients";
    model.addAttribute(MEAL_INGREDIENTS_DTO, new MealIngredientsDTO());
    getBreadcrumbs(titleString, model, request);
    return ADD_MEAL_INGREDIENTS_VIEW;

  }

  @PostMapping(ADD_MEAL_INGREDIENTS_MAPPING)
  public String viewAddMealIngredientsPost(
      @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      @Validated @ModelAttribute(MEAL_INGREDIENTS_DTO) MealIngredientsDTO mealIngredientsDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    foodTrackingDTO.getMealIngredientsDTOList().add(mealIngredientsDTO);
    redirectAttributes.addFlashAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    return REDIRECT + ADD_MEAL_DETAILS_MAPPING;

  }

  @GetMapping(ADD_MEAL_CHECK_MAPPING)
  public String viewAddMealCheck(
      @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      Model model,
      HttpServletRequest request) {

    titleString = "Add meal ingredients";
    getBreadcrumbs(titleString, model, request);
    return ADD_MEAL_CHECK_VIEW;

  }

  @PostMapping(ADD_MEAL_CHECK_MAPPING)
  public String viewAddMealIngredientsCheck(
      @Validated @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    foodTrackingService.saveMeal(foodTrackingDTO, ftUser);
    redirectAttributes.addFlashAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    return REDIRECT + FOOD_TRACKING_HOME_MAPPING;

  }

  @GetMapping(ADD_FOOD_TYPE_MAPPING)
  public String viewAddFoodType(
      Model model,
      HttpServletRequest request) {

    if (!model.containsAttribute(FOOD_TYPE_DTO) || !model.containsAttribute(ATTR_BINDING_RESULT + FOOD_TYPE_DTO)) {
      model.addAttribute(FOOD_TYPE_DTO, new FoodTypeDTO());
    }
    titleString = "Add food type";
    getBreadcrumbs(titleString, model, request);
    return ADD_FOOD_TYPE_VIEW;

  }

  @PostMapping(ADD_FOOD_TYPE_MAPPING)
  public String viewAddFoodTypePost(
      @Validated @ModelAttribute(FOOD_TYPE_DTO) FoodTypeDTO foodTypeDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    foodTypeValidator.validate(foodTypeDTO, bindingResult);
    redirectAttributes.addFlashAttribute(FOOD_TYPE_DTO, foodTypeDTO);
    if (bindingResult.hasErrors()) {
      redirectAttributes.addAttribute(ATTR_BINDING_RESULT + FOOD_TYPE_DTO, bindingResult);
      return REDIRECT + ADD_FOOD_TYPE_MAPPING;
    }
    foodTrackingService.saveFoodType(foodTypeDTO);
    return REDIRECT + getHomeMapping(FOOD_TRACKING_MAPPING);
  }

  @GetMapping(CREATE_MEAL_MAPPING)
  public String viewCreateStoredMeal(
      Model model,
      HttpServletRequest request) {

    if (!model.containsAttribute(STORED_MEAL_DTO)) {
      model.addAttribute(STORED_MEAL_DTO, foodTrackingService.createStoredMealDTO());
    }
    titleString = "Create stored meal";
    getBreadcrumbs(titleString, model, request);
    return CREATE_MEAL_VIEW;

  }

  @PostMapping(value = CREATE_MEAL_MAPPING, params = "submit")
  public String viewCreateStoredMealPost(
      @Validated @ModelAttribute(STORED_MEAL_DTO) StoredMealDTO storedMealDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
//    foodTypeValidator.validate(storedMealDTO, bindingResult);
    redirectAttributes.addFlashAttribute(STORED_MEAL_DTO, storedMealDTO);
    if (bindingResult.hasErrors()) {
      redirectAttributes.addAttribute(ATTR_BINDING_RESULT + STORED_MEAL_DTO, bindingResult);
      return REDIRECT + CREATE_MEAL_MAPPING;
    }
    foodTrackingService.saveStoredMeal(storedMealDTO);
    return REDIRECT + getHomeMapping(FOOD_TRACKING_MAPPING);
  }

  @PostMapping(value = CREATE_MEAL_MAPPING, params = "addMealIngredients")
  public String postAddStoredMealIngredients(
      @Validated @ModelAttribute(STORED_MEAL_DTO) StoredMealDTO storedMealDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {
//    foodTypeValidator.validate(storedMealDTO, bindingResult);
    redirectAttributes.addFlashAttribute(STORED_MEAL_DTO, storedMealDTO);
    redirectAttributes.addFlashAttribute(MEAL_INGREDIENTS_DTO, new MealIngredientsDTO());
    return REDIRECT + ADD_STORED_MEAL_INGREDIENTS_MAPPING;
  }

  @GetMapping(ADD_STORED_MEAL_INGREDIENTS_MAPPING)
  public String viewAddStoredMealIngredients(
      @ModelAttribute(STORED_MEAL_DTO) StoredMealDTO storedMealDTO,
      @ModelAttribute(MEAL_INGREDIENTS_DTO) MealIngredientsDTO mealIngredientsDTO,
      Model model,
      HttpServletRequest request) {

    titleString = "Add stored meal ingredients";
    getBreadcrumbs(titleString, model, request);
    return ADD_STORED_MEAL_INGREDIENTS_VIEW;

  }

  @PostMapping(ADD_STORED_MEAL_INGREDIENTS_MAPPING)
  public String viewAddStoredMealIngredientsPost(
      @ModelAttribute(STORED_MEAL_DTO) StoredMealDTO storedMealDTO,
      @Validated @ModelAttribute(MEAL_INGREDIENTS_DTO) MealIngredientsDTO mealIngredientsDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

//    foodTypeValidator.validate(storedMealDTO, bindingResult);
    redirectAttributes.addFlashAttribute(STORED_MEAL_DTO, storedMealDTO);
    redirectAttributes.addFlashAttribute(MEAL_INGREDIENTS_DTO, storedMealDTO);
    if (bindingResult.hasErrors()) {
      redirectAttributes.addAttribute(ATTR_BINDING_RESULT + MEAL_INGREDIENTS_DTO, bindingResult);
      return REDIRECT + ADD_STORED_MEAL_INGREDIENTS_MAPPING;
    }
    storedMealDTO.getMealIngredientsDTOList().add(mealIngredientsDTO);
    redirectAttributes.addFlashAttribute(STORED_MEAL_DTO, storedMealDTO);
//    foodTrackingService.saveFoodType(foodTypeDTO);
    return REDIRECT + CREATE_MEAL_MAPPING;
  }
}
