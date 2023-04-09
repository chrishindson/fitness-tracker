package com.chhin.fitnesstracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.chhin.fitnesstracker.util.Constants.BODY_MEASUREMENT_MAPPING;

@Controller
@RequestMapping(BODY_MEASUREMENT_MAPPING)
public class BodyMeasurementController extends AbstractController {

  public BodyMeasurementController() {
    super();
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Body measurement home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(BODY_MEASUREMENT_MAPPING);
  }
}
