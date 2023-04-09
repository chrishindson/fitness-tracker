package com.chhin.fitnesstracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.chhin.fitnesstracker.util.Constants.FOOD_TRACKING_MAPPING;

@Controller
@RequestMapping(FOOD_TRACKING_MAPPING)
public class FoodTrackingController extends AbstractController {

  public FoodTrackingController() {
    super();
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Food tracking home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(FOOD_TRACKING_MAPPING);
  }
}
