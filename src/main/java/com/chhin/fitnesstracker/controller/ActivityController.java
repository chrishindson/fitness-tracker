package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.entities.Activity;
import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.model.ActivityDTO;
import com.chhin.fitnesstracker.model.ActivityDetailsDTO;
import com.chhin.fitnesstracker.model.ActivityHistoryDTO;
import com.chhin.fitnesstracker.model.ActivitySummaryDTO;
import com.chhin.fitnesstracker.service.ActivityService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import com.chhin.fitnesstracker.validation.ActivityDetailsValidator;
import com.chhin.fitnesstracker.validation.ActivityValidator;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@SessionAttributes("activityDTO")
public class ActivityController extends AbstractController {

  public static final String ADD_ACTIVITY_MAPPING = "/activity/add-activity";
  public static final String ADD_ACTIVITY_DETAILS_MAPPING = "/activity/add-activity-details";
  public static final String ACTIVITY_HISTORY_MAPPING = "/activity/activity-history";
  public static final String ACTIVITY_DAILY_VIEW = "activity/activity-daily";
  public static final String ACTIVITY_HISTORY_VIEW = "activity/activity-history";
  public static final String ACTIVITY_DETAILS_VIEW = "activity/activity-details";
  public static final String ADD_ACTIVITY_VIEW = "activity/add-activity";
  public static final String ACTIVITY_HOME_MAPPING = "/activity/home";
  public static final String ACTIVITY_HOME_VIEW = "activity/home";
  private static final String ACTIVITY_DTO = "activityDTO";
  private static final String ACTIVITY_DETAILS_DTO = "activityDetailsDTO";
  private static final String ADD_ACTIVITY_DETAILS_VIEW = "activity/add-activity-details";
  private final LoggedInUserService loggedInUserService;
  private final ActivityService activityService;
  private final ActivityValidator activityValidator;
  private final ActivityDetailsValidator activityDetailsValidator;

  public ActivityController(ActivityService activityService,
                            ActivityValidator activityValidator,
                            LoggedInUserService loggedInUserService, ActivityDetailsValidator activityDetailsValidator) {
    super();
    this.activityService = activityService;
    this.activityValidator = activityValidator;
    this.loggedInUserService = loggedInUserService;
    this.activityDetailsValidator = activityDetailsValidator;
  }

  @GetMapping(ACTIVITY_HOME_MAPPING)
  public String viewHome(Model model,
                         HttpServletRequest request) {
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    ActivityHistoryDTO historyDTO = activityService.getAllTimeActivitySummaryByFtUserListJdbc(
        ftUser.getUsername());
    model.addAttribute("activityHistory", historyDTO);
    titleString = "Activity home";
    getBreadcrumbs(titleString, model, request);
    return ACTIVITY_HOME_VIEW;
  }

  @GetMapping(ADD_ACTIVITY_MAPPING)
  public String viewAddActivity(Model model,
                                HttpServletRequest request) {
    if (!model.containsAttribute(ACTIVITY_DTO)) {
      ActivityDTO activityDTO = new ActivityDTO();
      activityDTO.setActivityTypeList(activityService.getActivityTypeList());
      model.addAttribute(ACTIVITY_DTO, activityDTO);
    }
    titleString = "Add activity";
    getBreadcrumbs(titleString, model, request);
    return ADD_ACTIVITY_VIEW;
  }

  @PostMapping(value = ADD_ACTIVITY_MAPPING, params = "submit")
  public String viewAddActivity(
      @Validated @ModelAttribute(ACTIVITY_DTO) ActivityDTO activityDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {

    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    activityValidator.validate(activityDTO, bindingResult);
    redirectAttributes.addFlashAttribute(ACTIVITY_DTO, activityDTO);

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute(ATTR_BINDING_RESULT + ACTIVITY_DTO, bindingResult);
      return REDIRECT + ADD_ACTIVITY_MAPPING;
    }
    activityService.saveActivity(activityDTO, ftUser);
    return REDIRECT + ACTIVITY_HOME_MAPPING;
  }

  @PostMapping(value = ADD_ACTIVITY_MAPPING, params = "addActivityDetails")
  public String postAddActivityDetails(
      @ModelAttribute(ACTIVITY_DTO) ActivityDTO activityDetails,
      final RedirectAttributes redirectAttributes) {

    redirectAttributes.addFlashAttribute(ACTIVITY_DTO, activityDetails);
    redirectAttributes.addFlashAttribute(ACTIVITY_DETAILS_DTO, new ActivityDetailsDTO());
    return REDIRECT + ADD_ACTIVITY_DETAILS_MAPPING;
  }

  @GetMapping(ADD_ACTIVITY_DETAILS_MAPPING)
  public String viewAddActivityDetails(
      @ModelAttribute(ACTIVITY_DTO) ActivityDTO activityDTO,
      @ModelAttribute(ACTIVITY_DETAILS_DTO) ActivityDetailsDTO activityDetailsDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Add activity details";
    getBreadcrumbs(titleString, model, request);
    return ADD_ACTIVITY_DETAILS_VIEW;
  }

  @PostMapping(ADD_ACTIVITY_DETAILS_MAPPING)
  public String viewAddActivityDetails(
      @ModelAttribute(ACTIVITY_DTO) ActivityDTO activityDTO,
      @Validated @ModelAttribute(ACTIVITY_DETAILS_DTO) ActivityDetailsDTO activityDetailsDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {

    activityDetailsValidator.validate(activityDetailsDTO, bindingResult);
    redirectAttributes.addFlashAttribute(ACTIVITY_DTO, activityDTO);
    redirectAttributes.addFlashAttribute(ACTIVITY_DETAILS_DTO, activityDetailsDTO);

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute(ATTR_BINDING_RESULT + ACTIVITY_DTO, bindingResult);
      return REDIRECT + ADD_ACTIVITY_DETAILS_MAPPING;
    }
    activityDTO.getActivityDetailsList().add(activityDetailsDTO);
    return REDIRECT + ADD_ACTIVITY_MAPPING;
  }

  @GetMapping(ACTIVITY_HISTORY_MAPPING)
  public String viewActivityHistory(
      @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
      @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
      Model model, HttpServletRequest request) {

    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    titleString = "Activity history";

    Pageable pageable = PageRequest.of(page - 1, size);

    Page<ActivitySummaryDTO> activitySummaryDTOPage = activityService.getActivitySummaryByFtUserListJdbc(
        ftUser.getUsername(), pageable);
    model.addAttribute("activitySummaryDTOPage", activitySummaryDTOPage);
    getBreadcrumbs(titleString, model, request);
    return ACTIVITY_HISTORY_VIEW;
  }

  @GetMapping("/activity/activities/day/{activityDate}")
  public String viewActivityDaily(@PathVariable("activityDate") LocalDate activityDate,
                                  Model model, HttpServletRequest request) {

    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    titleString = "Activity " + activityDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));

    List<Activity> activityList = activityService.findByActivityDateAndUser(activityDate,
        ftUser);
    model.addAttribute("activityList", activityList);
    getBreadcrumbs(titleString, model, request);
    return ACTIVITY_DAILY_VIEW;
  }

  @GetMapping("/activity/activities/{activityId}")
  public String viewActivityRecord(@PathVariable("activityId") Long activityId,
                                   Model model, HttpServletRequest request) {

    titleString = "Activity";
    Activity activity = activityService.findByActivityId(activityId);
    model.addAttribute("activity", activity);
    getBreadcrumbs(titleString, model, request);
    return ACTIVITY_DETAILS_VIEW;
  }
}
