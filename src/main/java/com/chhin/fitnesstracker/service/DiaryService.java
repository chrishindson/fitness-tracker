package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.*;
import com.chhin.fitnesstracker.model.ActivitySummaryDTO;
import com.chhin.fitnesstracker.model.DailySummaryDTO;
import com.chhin.fitnesstracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DiaryService {

  @Autowired
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final ActivityRepository activityRepository;
  private final WeightTrackingRepository weightTrackingRepository;
  private final SleepTrackingRepository sleepTrackingRepository;
  private final TrainingPlanRepository trainingPlanRepository;
  private final BodyMeasurementRepository bodyMeasurementRepository;
  private final FoodTrackingRepository foodTrackingRepository;
  private final WaterTrackingRepository waterTrackingRepository;

  public DiaryService(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                      ActivityRepository activityRepository,
                      WeightTrackingRepository weightTrackingRepository,
                      SleepTrackingRepository sleepTrackingRepository,
                      TrainingPlanRepository trainingPlanRepository,
                      BodyMeasurementRepository bodyMeasurementRepository,
                      FoodTrackingRepository foodTrackingRepository,
                      WaterTrackingRepository waterTrackingRepository) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    this.activityRepository = activityRepository;
    this.weightTrackingRepository = weightTrackingRepository;
    this.sleepTrackingRepository = sleepTrackingRepository;
    this.trainingPlanRepository = trainingPlanRepository;
    this.bodyMeasurementRepository = bodyMeasurementRepository;
    this.foodTrackingRepository = foodTrackingRepository;
    this.waterTrackingRepository = waterTrackingRepository;
  }

  public DailySummaryDTO getDailySummary(FTUser user, LocalDate localDate) {
    DailySummaryDTO dailySummaryDTO = new DailySummaryDTO();
    dailySummaryDTO.setSummaryDate(localDate);

    Optional<WeightTracking> weightTracking = weightTrackingRepository.findWeightTrackingByRecordedDateAndFtUser(
        user, localDate);
    weightTracking.ifPresent(dailySummaryDTO::setWeightTracking);

    ActivitySummaryDTO summaryDTO = getDailyActivitySummaryByFtUserListJdbc(user.getUsername(),
        localDate);
    dailySummaryDTO.setActivitySummaryDTO(summaryDTO);

    Optional<SleepTracking> sleepTracking = sleepTrackingRepository.findSleepTrackingByFtUserAndRecordedDate(
        user, localDate);
    sleepTracking.ifPresent(dailySummaryDTO::setSleepTracking);

    List<TrainingPlan> trainingPlan = trainingPlanRepository.findTrainingPlanByFtUserAndActivityDate(
        user, localDate);
    dailySummaryDTO.setTrainingPlanList(trainingPlan);

    List<BodyMeasurement> bodyMeasurements = bodyMeasurementRepository.findBodyMeasurementByFtUserAndMeasurementDate(
        user, localDate);
    dailySummaryDTO.setBodyMeasurementList(bodyMeasurements);

    Optional<FoodTracking> foodTracking = foodTrackingRepository.findFoodTrackingByFoodTrackingDateAndFtUser(
        user, localDate);
    foodTracking.ifPresent(dailySummaryDTO::setFoodTracking);

    Optional<WaterTracking> waterTracking = waterTrackingRepository.findWaterTrackingByRecordedDateAndFtUser(
        user, localDate);
    waterTracking.ifPresent(dailySummaryDTO::setWaterTracking);
    return dailySummaryDTO;
  }

  public ActivitySummaryDTO getDailyActivitySummaryByFtUserListJdbc(String username,
                                                                    LocalDate activityDate) {
    String sql =
        "SELECT u.username, a.activity_date as activityDate, "
            + "count(1) as activityCount, "
            + "sum(coalesce(a.distance,0)) as totalDistance, "
            + "sum(a.calorie_count) as totalCalories, "
            + "sum(a.time_taken) as totalTime "
            + "FROM activity a, users u "
            + "WHERE lower(u.username) = lower(:username) "
            + "AND a.activity_date = :activityDate "
            + "group by u.username, a.activity_date";
    Map<String, Object> params = new HashMap<>();
    params.put("username", username);
    params.put("activityDate", activityDate);
    List<ActivitySummaryDTO> list = namedParameterJdbcTemplate.query(sql, params,
        new BeanPropertyRowMapper<>(ActivitySummaryDTO.class));
    if (list.isEmpty()) {
      return null;
    }
    return list.get(0);
  }
}
