package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.model.FoodTrackingDTO;
import com.chhin.fitnesstracker.model.FoodTypeDTO;
import com.chhin.fitnesstracker.model.MealDetailsDTO;
import com.chhin.fitnesstracker.service.FoodTrackingService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import com.chhin.fitnesstracker.validation.FoodTypeValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.chhin.fitnesstracker.util.Constants.FOOD_TRACKING_MAPPING;

@Controller
@RequestMapping(FOOD_TRACKING_MAPPING)
@SessionAttributes("foodTrackingDTO")
public class FoodTrackingController extends AbstractController {
  private static final String ADD_FOOD_TYPE_DTO = "foodTypeDTO";
  private static final String FOOD_TRACKING_DTO = "foodTrackingDTO";
  private final LoggedInUserService loggedInUserService;
  private final FoodTrackingService foodTrackingService;
  private final FoodTypeValidator foodTypeValidator;


  public FoodTrackingController(LoggedInUserService loggedInUserService, FoodTrackingService foodTrackingService, FoodTypeValidator foodTypeValidator) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.foodTrackingService = foodTrackingService;
    this.foodTypeValidator = foodTypeValidator;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Food tracking home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(FOOD_TRACKING_MAPPING);
  }

  @GetMapping("/add-food-details")
  public String viewAddFoodDetails(Model model, HttpServletRequest request) {

    if (!model.containsAttribute(FOOD_TRACKING_DTO) || !model.containsAttribute(FOOD_TRACKING_DTO + FOOD_TRACKING_DTO)) {
      model.addAttribute(FOOD_TRACKING_DTO, foodTrackingService.createFoodTrackingDTO());
    }
    titleString = "Add food details";
    getBreadcrumbs(titleString, model, request);
    return "food-tracking/add-food-details";

  }

  @PostMapping("/add-food-details")
  public String viewAddFoodDetailsPost(
      @Validated @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {


    redirectAttributes.addFlashAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    return REDIRECT + "/food-tracking/add-meal-type";

  }

  @GetMapping("/add-meal-type")
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
    return "food-tracking/add-meal-type";

  }

  @PostMapping("/add-meal-type")
  public String viewAddMealTypePost(
      @Validated @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {


    redirectAttributes.addFlashAttribute(FOOD_TRACKING_DTO, foodTrackingDTO);
    return REDIRECT + "/food-tracking/add-meal-ingredients";

  }

  @GetMapping("/add-meal-ingredients")
  public String viewAddMealIngredients(
      @ModelAttribute(FOOD_TRACKING_DTO) FoodTrackingDTO foodTrackingDTO,
      Model model,
      HttpServletRequest request) {

    titleString = "Add meal ingredients";
    getBreadcrumbs(titleString, model, request);
    return "food-tracking/add-meal-ingredients";

  }

  @GetMapping("/add-food-type")
  public String viewAddFoodType(Model model, HttpServletRequest request) {

    if (!model.containsAttribute(ADD_FOOD_TYPE_DTO) || !model.containsAttribute(ATTR_BINDING_RESULT + ADD_FOOD_TYPE_DTO)) {
      model.addAttribute(ADD_FOOD_TYPE_DTO, new FoodTypeDTO());
    }
    titleString = "Add food type";
    getBreadcrumbs(titleString, model, request);
    return "food-tracking/add-food-type";

  }

  @PostMapping("/add-food-type")
  public String viewAddFoodTypePost(@Validated @ModelAttribute(ADD_FOOD_TYPE_DTO) FoodTypeDTO foodTypeDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

    foodTypeValidator.validate(foodTypeDTO, bindingResult);
    redirectAttributes.addFlashAttribute(ADD_FOOD_TYPE_DTO, foodTypeDTO);
    if (bindingResult.hasErrors()) {
      redirectAttributes.addAttribute(ATTR_BINDING_RESULT + ADD_FOOD_TYPE_DTO, bindingResult);
      return REDIRECT + "/food-tracking/add-food-type";
    }
    foodTrackingService.saveFoodType(foodTypeDTO);
    return REDIRECT + getHomeMapping(FOOD_TRACKING_MAPPING);

  }
}
