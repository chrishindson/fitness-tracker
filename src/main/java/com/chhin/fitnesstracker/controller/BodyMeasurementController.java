package com.chhin.fitnesstracker.controller;

import static com.chhin.fitnesstracker.util.Constants.BODY_MEASUREMENT_MAPPING;

import com.chhin.fitnesstracker.config.exception.FitnessTrackerRuntimeException;
import com.chhin.fitnesstracker.entity.BodyMeasurementArea;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.model.BodyMeasurementDTO;
import com.chhin.fitnesstracker.service.BodyMeasurementService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(BODY_MEASUREMENT_MAPPING)
public class BodyMeasurementController extends AbstractController {
  private final LoggedInUserService loggedInUserService;
  private final BodyMeasurementService bodyMeasurementService;

  public BodyMeasurementController(
      LoggedInUserService loggedInUserService, BodyMeasurementService bodyMeasurementService) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.bodyMeasurementService = bodyMeasurementService;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Body measurement home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(BODY_MEASUREMENT_MAPPING);
  }

  @GetMapping("/add-body-measurement")
  public String inputForm(Model model, HttpServletRequest request) {
    BodyMeasurementDTO bodyMeasurementDTO = new BodyMeasurementDTO();
    bodyMeasurementDTO.setBodyMeasurementAreaList(
        bodyMeasurementService.findAllBodyMeasurementAreas().stream()
            .collect(
                Collectors.toMap(
                    BodyMeasurementArea::getBodyMeasurementAreaId,
                    BodyMeasurementArea::getBodyMeasurementAreaDescription)));
    titleString = "Add body measurement";
    getBreadcrumbs(titleString, model, request);
    model.addAttribute("bodyMeasurementDTO", bodyMeasurementDTO);
    return "body-measurement/add-body-measurement";
  }

  @PostMapping("/add-body-measurement")
  public String inputSubmit(
          @Validated @ModelAttribute("bodyMeasurementDTO") BodyMeasurementDTO bodyMeasurementDTO,
          RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("bodyMeasurementDTO", bodyMeasurementDTO);
    return "redirect:/body-measurement/check";
  }

  @GetMapping("/check")
  public String checkForm(
      @ModelAttribute("bodyMeasurementDTO") BodyMeasurementDTO bodyMeasurementDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Check body measurement";
    getBreadcrumbs(titleString, model, request);
    return "body-measurement/check-body-measurement";
  }

  @PostMapping("/check")
  public String checkSubmit(@ModelAttribute("bodyMeasurementDTO") BodyMeasurementDTO bodyMeasurementDTO, HttpServletRequest request) {
    FTUser ftUser =
        loggedInUserService.getLoggedInUser().orElseThrow(FitnessTrackerRuntimeException::new);
    return "redirect:/body-measurement/home";
  }

  @GetMapping("/history")
  public String historyForm(Model model, HttpServletRequest request) {
    titleString = "History";
    getBreadcrumbs(titleString, model, request);
    model.addAttribute("history", new ArrayList<BodyMeasurementDTO>());
    return "body-measurement/history";
  }

  @PostMapping("/history")
  public String historySubmit(HttpServletRequest request) {
    return "redirect:/body-measurement/record";
  }

  @GetMapping("/record")
  public String recordForm(Model model, HttpServletRequest request) {
    titleString = "Record";
    getBreadcrumbs(titleString, model, request);
    return "body-measurement/record";
  }
}
