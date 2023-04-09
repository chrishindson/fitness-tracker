package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.model.SleepTrackingDTO;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import com.chhin.fitnesstracker.service.SleepTrackingService;
import com.chhin.fitnesstracker.validation.SleepTrackingValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.chhin.fitnesstracker.util.Constants.SLEEP_TRACKING_MAPPING;

@Controller
@RequestMapping(SLEEP_TRACKING_MAPPING)
public class SleepTrackingController extends AbstractController {
  private static final String SLEEP_TRACKING_DTO = "sleepTrackingDTO";
  private final LoggedInUserService loggedInUserService;
  private final SleepTrackingService sleepTrackingService;
  private final SleepTrackingValidator sleepTrackingValidator;

  public SleepTrackingController(LoggedInUserService loggedInUserService, SleepTrackingService sleepTrackingService, SleepTrackingValidator sleepTrackingValidator) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.sleepTrackingService = sleepTrackingService;
    this.sleepTrackingValidator = sleepTrackingValidator;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Sleep tracking home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(SLEEP_TRACKING_MAPPING);
  }

  @GetMapping("/add-sleep-details")
  public String viewAddSleepDetails(Model model, HttpServletRequest request) {
    if (!model.containsAttribute(SLEEP_TRACKING_DTO) || !model.containsAttribute(ATTR_BINDING_RESULT + SLEEP_TRACKING_DTO)) {
      model.addAttribute(SLEEP_TRACKING_DTO, new SleepTrackingDTO());
    }
    titleString = "Add sleep details";
    getBreadcrumbs(titleString, model, request);
    return "sleep-tracking/add-sleep-details";
  }

  @PostMapping("/add-sleep-details")
  public String viewAddSleepDetailsPost(
      @Validated @ModelAttribute(SLEEP_TRACKING_DTO) SleepTrackingDTO sleepTrackingDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);

    sleepTrackingValidator.validate(sleepTrackingDTO, bindingResult);

    if (bindingResult.hasErrors()) {
      redirectAttributes.addAttribute(ATTR_BINDING_RESULT + SLEEP_TRACKING_DTO, bindingResult);
      return REDIRECT + "sleep-tracking/add-sleep-details";
    }


    sleepTrackingService.saveSleepTracking(sleepTrackingDTO, ftUser);
    return REDIRECT + getHomeMapping(SLEEP_TRACKING_MAPPING);
  }
}
