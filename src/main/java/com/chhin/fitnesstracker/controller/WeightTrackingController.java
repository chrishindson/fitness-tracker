package com.chhin.fitnesstracker.controller;

import static com.chhin.fitnesstracker.util.Constants.WEIGHT_TRACKING_MAPPING;

import com.chhin.fitnesstracker.config.exception.FitnessTrackerRuntimeException;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.model.WeightTrackingDTO;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import com.chhin.fitnesstracker.service.WeightTrackingService;
import com.chhin.fitnesstracker.validation.WeightTrackingValidator;
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

@Controller
@RequestMapping(WEIGHT_TRACKING_MAPPING)
public class WeightTrackingController extends AbstractController {

  public static final String WEIGHT_TRACKING_DTO = "weightTrackingDTO";
  private final LoggedInUserService loggedInUserService;
  private final WeightTrackingService weightTrackingService;
  private final WeightTrackingValidator weightTrackingValidator;

  public WeightTrackingController(
      LoggedInUserService loggedInUserService,
      WeightTrackingService weightTrackingService,
      WeightTrackingValidator weightTrackingValidator) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.weightTrackingService = weightTrackingService;
    this.weightTrackingValidator = weightTrackingValidator;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Weight tracking home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(WEIGHT_TRACKING_MAPPING);
  }

  @GetMapping("/add-weight-details")
  public String viewAddActivity(Model model, HttpServletRequest request) {
    if (!model.containsAttribute(WEIGHT_TRACKING_DTO)) {
      WeightTrackingDTO weightTrackingDTO = new WeightTrackingDTO();
      model.addAttribute(WEIGHT_TRACKING_DTO, weightTrackingDTO);
    }
    titleString = "Add weight details";
    getBreadcrumbs(titleString, model, request);
    return "weight-tracking/add-weight-details";
  }

  @PostMapping(value = "/add-weight-details", params = "submit")
  public String viewAddActivity(
      @Validated @ModelAttribute(WEIGHT_TRACKING_DTO) WeightTrackingDTO weightTrackingDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {

    FTUser ftUser =
        loggedInUserService.getLoggedInUser().orElseThrow(FitnessTrackerRuntimeException::new);
    weightTrackingValidator.validate(weightTrackingDTO, bindingResult);
    redirectAttributes.addFlashAttribute(WEIGHT_TRACKING_DTO, weightTrackingDTO);

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute(
          ATTR_BINDING_RESULT + WEIGHT_TRACKING_DTO, bindingResult);
      return REDIRECT + "/weight-tracking/add-weight-details";
    }
    weightTrackingService.saveWeightDetails(weightTrackingDTO, ftUser);
    return REDIRECT + getHomeMapping(WEIGHT_TRACKING_MAPPING);
  }
}
