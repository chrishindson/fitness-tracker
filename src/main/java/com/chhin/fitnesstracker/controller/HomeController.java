package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.model.ActivityDiaryDTO;
import com.chhin.fitnesstracker.model.ActivityMonthlyDTO;
import com.chhin.fitnesstracker.model.LoginFormDTO;
import com.chhin.fitnesstracker.service.ActivityService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("loginForm")
public class HomeController extends AbstractController {

  private static final String LOGIN_MAPPING = "/login";
  private final LoggedInUserService loggedInUserService;
  private final ActivityService activityService;

  public HomeController(LoggedInUserService loggedInUserService, ActivityService activityService) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.activityService = activityService;
  }

  @GetMapping({LOGIN_MAPPING, "/"})
  public String viewLogin(
      final Model model
  ) {
    if (model.getAttribute("loginForm") == null || model.getAttribute("loginForm2") == null) {
      model.addAttribute("loginForm", new LoginFormDTO());
    }
    populateTitle(model, "Login");
    return "login";
  }

  @GetMapping("/dashboard")
  public String viewDashboard(Model model, HttpServletRequest request) {
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    ActivityMonthlyDTO activityMonthlyDTO = new ActivityMonthlyDTO(LocalDate.now());
    List<ActivityDiaryDTO> diaryDTOList = activityService.getDiaryFtUserListJdbc(
        ftUser.getUsername(), activityMonthlyDTO.getActivityMonth());
    activityMonthlyDTO.setMonthlyActivities(diaryDTOList);
    model.addAttribute("activityMonthlyDTO", activityMonthlyDTO);
    titleString = "Dashboard";
    getStarterBreadcrumbs(titleString, model, request);
    return "dashboard";
  }
}
