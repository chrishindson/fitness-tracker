package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.model.BodyMeasurementDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

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

  @GetMapping("/input")
  public String inputForm(Model model, HttpServletRequest request) {
    String titleString = "Input";
    getBreadcrumbs(titleString, model, request);
    model.addAttribute("bodyMeasurementDTO", new BodyMeasurementDTO());
    return "body-measurement/input";
  }

  @PostMapping("/input")
  public String inputSubmit(BodyMeasurementDTO bodyMeasurementDTO, HttpServletRequest request) {
    // handle form submission
    return "redirect:/body-measurement/check";
  }

  @GetMapping("/check")
  public String checkForm(Model model, HttpServletRequest request) {
    String titleString = "Check";
    getBreadcrumbs(titleString, model, request);
    // retrieve submitted data
    model.addAttribute("bodyMeasurementDTO", new BodyMeasurementDTO());
    return "body-measurement/check";
  }

  @PostMapping("/check")
  public String checkSubmit(BodyMeasurementDTO bodyMeasurementDTO, HttpServletRequest request) {
    // handle form submission
    return "redirect:/body-measurement/home";
  }

  @GetMapping("/history")
  public String historyForm(Model model, HttpServletRequest request) {
    String titleString = "History";
    getBreadcrumbs(titleString, model, request);
    // retrieve history data
    model.addAttribute("history", new ArrayList<BodyMeasurementDTO>());
    return "body-measurement/history";
  }

  @PostMapping("/history")
  public String historySubmit(HttpServletRequest request) {
    // handle form submission
    return "redirect:/body-measurement/record";
  }

  @GetMapping("/record")
  public String recordForm(Model model, HttpServletRequest request) {
    String titleString = "Record";
    getBreadcrumbs(titleString, model, request);
    // retrieve record data
    model.addAttribute("record", new BodyMeasurementDTO());
    return "body-measurement/record";
  }
}
