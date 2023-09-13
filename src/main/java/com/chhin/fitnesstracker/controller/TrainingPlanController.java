package com.chhin.fitnesstracker.controller;

import static com.chhin.fitnesstracker.util.Constants.TRAINING_PLAN_MAPPING;

import com.chhin.fitnesstracker.FTRuntimeException;
import com.chhin.fitnesstracker.entity.ActivityType;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.model.TrainingPlanDTO;
import com.chhin.fitnesstracker.service.ActivityService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import com.chhin.fitnesstracker.service.TrainingPlanService;
import com.chhin.fitnesstracker.validation.TrainingPlanValidator;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(TRAINING_PLAN_MAPPING)
public class TrainingPlanController extends AbstractController {
  public static final String TRAINING_PLAN_DTO = "trainingPlanDTO";
  private final LoggedInUserService loggedInUserService;
  private final ActivityService activityService;
  private final TrainingPlanService trainingPlanService;
  private final TrainingPlanValidator trainingPlanValidator;

  public TrainingPlanController(
      LoggedInUserService loggedInUserService,
      ActivityService activityService,
      TrainingPlanService trainingPlanService, TrainingPlanValidator trainingPlanValidator) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.activityService = activityService;
    this.trainingPlanService = trainingPlanService;
    this.trainingPlanValidator = trainingPlanValidator;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Training plan home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(TRAINING_PLAN_MAPPING);
  }

  @GetMapping("/create-training-plan")
  public String inputForm(Model model, HttpServletRequest request) {
    String titleString = "Create training plan";
    TrainingPlanDTO trainingPlanDTO = new TrainingPlanDTO();
    trainingPlanDTO.setLogThursday(true);
    trainingPlanDTO.setActivityTypeList(
        activityService.getActivityTypeList().stream()
            .collect(
                Collectors.toMap(
                    ActivityType::getActivityTypeId, ActivityType::getActivityTypeDescription)));
    getBreadcrumbs(titleString, model, request);
    model.addAttribute(TRAINING_PLAN_DTO, trainingPlanDTO);
    return "training-plan/create-training-plan";
  }

  @PostMapping("/create-training-plan")
  public String inputSubmit(
      @Validated @ModelAttribute(TRAINING_PLAN_DTO) TrainingPlanDTO trainingPlanDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    // handle form submission
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElseThrow(FTRuntimeException::new);
    trainingPlanValidator.validate(trainingPlanDTO, bindingResult);
    bindingResult.getAllErrors().forEach(System.out::println);
    redirectAttributes.addFlashAttribute(TRAINING_PLAN_DTO, trainingPlanDTO);
    if (bindingResult.hasErrors()){
      redirectAttributes.addFlashAttribute(ATTR_BINDING_RESULT + TRAINING_PLAN_DTO, bindingResult );
      return "redirect:/training-plan/create-training-plan";
    }
    trainingPlanService.saveTrainingPlan(trainingPlanDTO, ftUser);
      return "redirect:/training-plan/home";
  }

  @GetMapping("/check-training-plan")
  public String checkForm(
      @ModelAttribute(TRAINING_PLAN_DTO) TrainingPlanDTO trainingPlanDTO,
      Model model,
      HttpServletRequest request) {
    String titleString = "Check";
    getBreadcrumbs(titleString, model, request);
    return "training-plan/check-training-plan";
  }

  @PostMapping("/check-training-plan")
  public String checkSubmit(
      @ModelAttribute(TRAINING_PLAN_DTO) TrainingPlanDTO trainingPlanDTO,
      HttpServletRequest request) {
    // handle form submission
    return "redirect:/training-plan/home";
  }

  @GetMapping("/history")
  public String historyForm(Model model, HttpServletRequest request) {
    String titleString = "History";
    getBreadcrumbs(titleString, model, request);
    // retrieve history data
    model.addAttribute("history", new ArrayList<TrainingPlanDTO>());
    return "training-plan/history";
  }

  @PostMapping("/history")
  public String historySubmit(HttpServletRequest request) {
    // handle form submission
    return "redirect:/training-plan/record";
  }

  @GetMapping("/record")
  public String recordForm(Model model, HttpServletRequest request) {
    String titleString = "Record";
    getBreadcrumbs(titleString, model, request);
    // retrieve record data
    model.addAttribute("record", new TrainingPlanDTO());
    return "training-plan/record";
  }
}
