package com.chhin.fitnesstracker.controller;

import static com.chhin.fitnesstracker.util.Constants.USERS_MAPPING;

import com.chhin.fitnesstracker.config.exception.FitnessTrackerRuntimeException;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(USERS_MAPPING)
public class UserController extends AbstractController {

  private final LoggedInUserService loggedInUserService;

  public UserController(LoggedInUserService loggedInUserService) {
    super();
    this.loggedInUserService = loggedInUserService;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    FTUser user = loggedInUserService.getLoggedInUser().orElseThrow(FitnessTrackerRuntimeException::new);
    titleString = "User management home";
    model.addAttribute("user", user);
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(USERS_MAPPING);
  }

  @GetMapping("/users/amend/{userId}")
  public String viewUserDetails(Model model, HttpServletRequest request) {
    FTUser user = loggedInUserService.getLoggedInUser().orElseThrow(FitnessTrackerRuntimeException::new);
    if (user == null) {
      return REDIRECT + "/home";
    }
    titleString = user.getFullName();
    getBreadcrumbs(titleString, model, request);
    return "users/amend-user";
  }
}
