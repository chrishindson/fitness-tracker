package com.chhin.fitnesstracker.controller;

import static com.chhin.fitnesstracker.util.Constants.WATER_TRACKING_MAPPING;

import com.chhin.fitnesstracker.model.WaterTrackingDTO;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

  @GetMapping("/input")
  public String inputForm(Model model, HttpServletRequest request) {
    String titleString = "Input";
    getBreadcrumbs(titleString, model, request);
    model.addAttribute("waterTrackingDTO", new WaterTrackingDTO());
    return "water-tracking/input";
  }

  @PostMapping("/input")
  public String inputSubmit(WaterTrackingDTO waterTrackingDTO, HttpServletRequest request) {
    // handle form submission
    return "redirect:/water-tracking/check";
  }

  @GetMapping("/check")
  public String checkForm(Model model, HttpServletRequest request) {
    String titleString = "Check";
    getBreadcrumbs(titleString, model, request);
    // retrieve submitted data
    model.addAttribute("waterTrackingDTO", new WaterTrackingDTO());
    return "water-tracking/check";
  }

  @PostMapping("/check")
  public String checkSubmit(WaterTrackingDTO waterTrackingDTO, HttpServletRequest request) {
    // handle form submission
    return "redirect:/water-tracking/home";
  }

  @GetMapping("/history")
  public String historyForm(Model model, HttpServletRequest request) {
    String titleString = "History";
    getBreadcrumbs(titleString, model, request);
    // retrieve history data
    model.addAttribute("history", new ArrayList<WaterTrackingDTO>());
    return "water-tracking/history";
  }

  @PostMapping("/history")
  public String historySubmit(HttpServletRequest request) {
    // handle form submission
    return "redirect:/water-tracking/record";
  }

  @GetMapping("/record")
  public String recordForm(Model model, HttpServletRequest request) {
    String titleString = "Record";
    getBreadcrumbs(titleString, model, request);
    // retrieve record data
    model.addAttribute("record", new WaterTrackingDTO());
    return "water-tracking/record";
  }
}
