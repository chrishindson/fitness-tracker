package com.chhin.fitnesstracker.controller;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.model.ActivityDiaryDTO;
import com.chhin.fitnesstracker.model.ActivityMonthlyDTO;
import com.chhin.fitnesstracker.model.DailySummaryDTO;
import com.chhin.fitnesstracker.service.ActivityService;
import com.chhin.fitnesstracker.service.DiaryService;
import com.chhin.fitnesstracker.service.LoggedInUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.chhin.fitnesstracker.util.Constants.DIARY_MAPPING;

@Controller
@RequestMapping(DIARY_MAPPING)
public class DiaryController extends AbstractController {

  public static final String DAILY_SUMMARY_DTO = "dailySummaryDTO";
  private final LoggedInUserService loggedInUserService;
  private final ActivityService activityService;
  private final DiaryService diaryService;

  public DiaryController(LoggedInUserService loggedInUserService,
                         ActivityService activityService, DiaryService diaryService) {
    super();
    this.loggedInUserService = loggedInUserService;
    this.activityService = activityService;
    this.diaryService = diaryService;
  }

  @GetMapping(CONTROLLER_HOME_LANDING)
  public String viewHome(Model model, HttpServletRequest request) {
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    ActivityMonthlyDTO activityMonthlyDTO = new ActivityMonthlyDTO(LocalDate.now());
    List<ActivityDiaryDTO> diaryDTOList = activityService.getDiaryFtUserListJdbc(
        ftUser.getUsername(), activityMonthlyDTO.getActivityMonth());
    activityMonthlyDTO.setMonthlyActivities(diaryDTOList);
    model.addAttribute("activityMonthlyDTO", activityMonthlyDTO);
    titleString = "Diary";
    getBreadcrumbs(titleString, model, request);
    return getHomeMapping(DIARY_MAPPING);
  }

  @GetMapping("/day")
  public String viewDailyOverview(
      @RequestParam("diaryDate") String diaryDate,
      Model model,
      HttpServletRequest request) {
    LocalDate diaryLocalDate = LocalDate.parse(diaryDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
    FTUser ftUser = loggedInUserService.getLoggedInUser().orElse(null);
    DailySummaryDTO dailySummaryDTO = diaryService.getDailySummary(ftUser, diaryLocalDate);
    model.addAttribute(DAILY_SUMMARY_DTO, dailySummaryDTO);

    titleString =
        "Diary - " + diaryLocalDate.format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
    getBreadcrumbs(titleString, model, request);
    return "diary/diary-daily-new";
  }

}
