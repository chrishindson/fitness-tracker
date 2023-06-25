package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.model.TrainingPlanDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

import static com.chhin.fitnesstracker.util.Constants.TRAINING_PLAN_MAPPING;

@Controller
@RequestMapping(TRAINING_PLAN_MAPPING)
public class TrainingPlanController extends AbstractController {

  public TrainingPlanController() {
    super();
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Training plan home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(TRAINING_PLAN_MAPPING);
  }

  @GetMapping("/input")
  public String inputForm(Model model, HttpServletRequest request) {
    String titleString = "Input";
    getBreadcrumbs(titleString, model, request);
    model.addAttribute("trainingPlanDTO", new TrainingPlanDTO());
    return "training-plan/input";
  }

  @PostMapping("/input")
  public String inputSubmit(TrainingPlanDTO trainingPlanDTO, HttpServletRequest request) {
    // handle form submission
    return "redirect:/training-plan/check";
  }

  @GetMapping("/check")
  public String checkForm(Model model, HttpServletRequest request) {
    String titleString = "Check";
    getBreadcrumbs(titleString, model, request);
    // retrieve submitted data
    model.addAttribute("trainingPlanDTO", new TrainingPlanDTO());
    return "training-plan/check";
  }

  @PostMapping("/check")
  public String checkSubmit(TrainingPlanDTO trainingPlanDTO, HttpServletRequest request) {
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
