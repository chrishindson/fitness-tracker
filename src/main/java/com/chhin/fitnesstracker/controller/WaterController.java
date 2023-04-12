package com.chhin.fitnesstracker.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.chhin.fitnesstracker.util.Constants.WATER_TRACKING_MAPPING;

@Controller
@RequestMapping(WATER_TRACKING_MAPPING)
public class WaterController extends AbstractController {

  public WaterController() {
    super();
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    titleString = "Water tracking home";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(WATER_TRACKING_MAPPING);
  }
}
