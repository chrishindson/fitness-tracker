package com.chhin.fitnesstracker.controller;

import static com.chhin.fitnesstracker.util.Constants.MENTAL_HEALTH_LOG_MAPPING;

import com.chhin.fitnesstracker.config.exception.FitnessTrackerRuntimeException;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.model.MentalHealthLogDTO;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import com.chhin.fitnesstracker.service.MentalHealthLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(MENTAL_HEALTH_LOG_MAPPING)
@SessionAttributes("mentalHealthLogDTO")
public class MentalHealthLogController extends AbstractController {
  private static final String MENTAL_HEALTH_LOG_DTO = "mentalHealthLogDTO";
  private final LoggedInUserService loggedInUserService;
  private final MentalHealthLogService mentalHealthLogService;

  public MentalHealthLogController(LoggedInUserService loggedInUserService, MentalHealthLogService mentalHealthLogService) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.mentalHealthLogService = mentalHealthLogService;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(
      Model model,
      HttpServletRequest request) {
    titleString = "Mental health log home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(MENTAL_HEALTH_LOG_MAPPING);
  }

  @GetMapping("/add-mental-health-log")
  public String viewMentalHealthLog(
      Model model,
      HttpServletRequest request) {
    titleString = "Log date";
    model.addAttribute(MENTAL_HEALTH_LOG_DTO, new MentalHealthLogDTO());
    getBreadcrumbs(titleString, model, request);
    return "mental-health-log/add-mental-health-log";
  }

  @PostMapping("/add-mental-health-log")
  public String viewMentalHealthLogPost(
      @Validated @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(MENTAL_HEALTH_LOG_DTO, mentalHealthLogDTO);
    return REDIRECT + "/mental-health-log/thoughts-emotions";
  }

  @GetMapping("/thoughts-emotions")
  public String viewThoughtsAndEmotions(
      @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Thoughts and emotions";
    getBreadcrumbs(titleString, model, request);
    return "mental-health-log/thoughts-emotions";
  }

  @PostMapping("/thoughts-emotions")
  public String viewThoughtsAndEmotionsPost(
      @Validated @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(MENTAL_HEALTH_LOG_DTO, mentalHealthLogDTO);
    return REDIRECT + "/mental-health-log/potential-triggers";
  }

  @GetMapping("/potential-triggers")
  public String viewPotentialTriggers(
      @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Potential triggers";
    getBreadcrumbs(titleString, model, request);
    return "mental-health-log/potential-triggers";
  }

  @PostMapping("/potential-triggers")
  public String viewPotentialTriggersPost(
      @Validated @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(MENTAL_HEALTH_LOG_DTO, mentalHealthLogDTO);
    return REDIRECT + "/mental-health-log/intensity";
  }

  @GetMapping("/intensity")
  public String viewIntensity(
      @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Intensity";
    getBreadcrumbs(titleString, model, request);
    return "mental-health-log/intensity";
  }

  @PostMapping("/intensity")
  public String viewIntensityPost(
      @Validated @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(MENTAL_HEALTH_LOG_DTO, mentalHealthLogDTO);
    return REDIRECT + "/mental-health-log/actual-response";
  }

  @GetMapping("/actual-response")
  public String viewActualResponse(
      @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Actual response";
    getBreadcrumbs(titleString, model, request);
    return "mental-health-log/actual-response";
  }

  @PostMapping("/actual-response")
  public String viewActualResponsePost(
      @Validated @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(MENTAL_HEALTH_LOG_DTO, mentalHealthLogDTO);
    return REDIRECT + "/mental-health-log/future-response";
  }

  @GetMapping("/future-response")
  public String viewFutureResponse(
      @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Future response";
    getBreadcrumbs(titleString, model, request);
    return "mental-health-log/future-response";
  }

  @PostMapping("/future-response")
  public String viewFutureResponsePost(
      @Validated @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute(MENTAL_HEALTH_LOG_DTO, mentalHealthLogDTO);
    return REDIRECT + "/mental-health-log/additional-notes";
  }

  @GetMapping("/additional-notes")
  public String viewAdditionalNotes(
      @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      Model model,
      HttpServletRequest request) {
    titleString = "Additional notes";
    getBreadcrumbs(titleString, model, request);
    return "mental-health-log/additional-notes";
  }

  @PostMapping("/additional-notes")
  public String viewAdditionalNotesPost(
      @Validated @ModelAttribute(MENTAL_HEALTH_LOG_DTO) MentalHealthLogDTO mentalHealthLogDTO,
      final BindingResult bindingResult,
      final RedirectAttributes redirectAttributes) {
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElseThrow(FitnessTrackerRuntimeException::new);
    mentalHealthLogService.save(mentalHealthLogDTO, ftUser);

    return REDIRECT + getHomeMapping(MENTAL_HEALTH_LOG_MAPPING);
  }

}
