package com.chhin.fitnesstracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
