package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.Activity;
import com.chhin.fitnesstracker.entity.ActivityDetails;
import com.chhin.fitnesstracker.entity.ActivityType;
import com.chhin.fitnesstracker.entity.FTUser;
// import com.chhin.fitnesstracker.mapping.ActivityMapper;
import com.chhin.fitnesstracker.model.ActivityDTO;
import com.chhin.fitnesstracker.model.ActivityDetailsDTO;
import com.chhin.fitnesstracker.model.ActivityDiaryDTO;
import com.chhin.fitnesstracker.model.ActivitySummaryDTO;
import com.chhin.fitnesstracker.model.history.ActivityHistoryDTO;
import com.chhin.fitnesstracker.repository.ActivityDetailsRepository;
import com.chhin.fitnesstracker.repository.ActivityRepository;
import com.chhin.fitnesstracker.repository.ActivityTypeRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

  public static final String USERNAME = "username";
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final ActivityRepository activityRepository;
  private final ActivityTypeRepository activityTypeRepository;
  //  private final ActivityMapper activityMapper;
  private final ActivityDetailsRepository activityDetailsRepository;

  public ActivityService(
      NamedParameterJdbcTemplate namedParameterJdbcTemplate,
      ActivityRepository activityRepository,
      ActivityTypeRepository activityTypeRepository,
      ActivityDetailsRepository activityDetailsRepository
      //      ActivityMapper activityMapper
      ) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.activityRepository = activityRepository;
    this.activityTypeRepository = activityTypeRepository;
    //    this.activityMapper = activityMapper;
    this.activityDetailsRepository = activityDetailsRepository;
  }

  public void saveActivity(ActivityDTO dto, FTUser user) {
    ActivityType activityType =
        activityTypeRepository.findById(Long.valueOf(dto.getActivityType())).orElse(null);

    Activity activity = // activityMapper.toActivity(dto);
        Activity.builder()
            .activityType(activityType)
            .activityDate(dto.getActivityDate().toLocalDate())
            .distance(dto.getDistance() != null ? dto.getDistance() : null)
            .startTime(dto.getStartTime().toLocalTime())
            .notes(dto.getNotes().isEmpty() ? null : dto.getNotes())
            .calorieCount(dto.getCalorieCount())
            .timeTaken(dto.getTimeTaken().toLocalTime())
            .ftUser(user)
            .build();
    Activity savedActivity = activityRepository.save(activity);

    if (!dto.getActivityDetailsList().isEmpty()) {
      for (ActivityDetailsDTO detailsDTO : dto.getActivityDetailsList()) {
        ActivityDetails details =
            ActivityDetails.builder()
                .activity(savedActivity)
                .repetitions(detailsDTO.getRepetitions())
                .numberOfSets(detailsDTO.getNumberOfSets())
                .weight(
                    (detailsDTO.getWeight() != null
                            && detailsDTO.getWeight().compareTo(BigDecimal.ZERO) != 0)
                        ? detailsDTO.getWeight()
                        : null)
                .build();
        activityDetailsRepository.save(details);
      }
    }
  }

  public List<Activity> findByActivityDateAndUser(LocalDate activityDate, FTUser user) {
    return activityRepository.findByActivityDateAndUser(activityDate, user);
  }

  public List<ActivityType> getActivityTypeList() {
    return activityTypeRepository.getActivityTypeList();
  }

  public Page<Activity> getActivitiesByFtUser(String username, Pageable pageable) {
    return activityRepository.getActivitiesByFtUser(username, pageable);
  }

  public Page<ActivitySummaryDTO> getActivitySummaryByFtUser(String username, Pageable pageable) {
    return activityRepository.getActivitySummaryByFtUser(username, pageable);
  }

  public Page<ActivitySummaryDTO> getActivitySummaryByFtUserListJdbc(
      FTUser user, Pageable pageable) {
    String sql =
        "SELECT u.username, a.activity_date as activityDate, count(1) as activityCount, sum(coalesce(a.distance,0)) as totalDistance, "
            + "sum(a.calorie_count) as totalCalories, "
            + "sum(a.time_taken) as totalTime "
            + "FROM activity a, users u "
            + "WHERE lower(u.username) = lower(:username) "
            + "group by u.username, a.activity_date "
            + "ORDER BY a.activity_date desc ";
    Map<String, Object> params = new HashMap<>();
    params.put(USERNAME, user.getUsername());
    List<ActivitySummaryDTO> list =
        namedParameterJdbcTemplate.query(
            sql, params, new BeanPropertyRowMapper<>(ActivitySummaryDTO.class));
    final int start = (int) pageable.getOffset();
    final int end = Math.min((start + pageable.getPageSize()), list.size());
    return new PageImpl<>(list.subList(start, end), pageable, list.size());
  }

  public List<ActivityDiaryDTO> getDiaryFtUserListJdbc(String username, LocalDate diaryMonth) {
    String sql =
        "SELECT u.username, a.activity_date as activityDate, "
            + "count(1) as activityCount, "
            + "sum(coalesce(a.distance,0)) as totalDistance, "
            + "sum(a.calorie_count) as totalCalories, "
            + "sum(a.time_taken) as totalTime "
            + "FROM activity a, users u "
            + "WHERE lower(u.username) = lower(:username) "
            + "AND a.activity_date between date_trunc('month',:diaryMonth) "
            + "and date_trunc('month',:diaryMonth + INTERVAL '1 MONTH - 1 day') "
            + "group by u.username, a.activity_date";
    Map<String, Object> params = new HashMap<>();
    params.put(USERNAME, username);
    params.put("diaryMonth", diaryMonth);
    return namedParameterJdbcTemplate.query(
        sql, params, new BeanPropertyRowMapper<>(ActivityDiaryDTO.class));
  }

  public ActivityHistoryDTO getAllTimeActivitySummaryByFtUserListJdbc(FTUser user) {
    String sql =
        "SELECT u.username, "
            + "count(1) as activityCount, "
            + "sum(coalesce(a.distance,0)) as totalDistance, "
            + "sum(a.calorie_count) as totalCalories, "
            + "sum(a.time_taken)::text  as totalTime "
            + "FROM activity a, users u "
            + "WHERE lower(u.username) = lower(:username) "
            + "group by u.username";
    Map<String, Object> params = new HashMap<>();
    params.put(USERNAME, user.getUsername());
    List<ActivityHistoryDTO> list =
        namedParameterJdbcTemplate.query(
            sql, params, new BeanPropertyRowMapper<>(ActivityHistoryDTO.class));
    return list.get(0);
  }

  public Activity findByActivityId(Long activityId) {
    return activityRepository.findById(activityId).orElse(null);
  }

  public ActivityDTO findByActivityDTOByActivityId(Long activityId) {
    Activity activity = activityRepository.findById(activityId).orElse(null);
    if (activity == null) {
      return null;
    }
    ActivityDTO activityDTO = new ActivityDTO(activity);
    activityDTO.setActivityTypeList(
        getActivityTypeList().stream()
            .collect(
                Collectors.toMap(
                    ActivityType::getActivityTypeId, ActivityType::getActivityTypeDescription)));
    activityDTO.setActivityType(String.valueOf(activity.getActivityType().getActivityTypeId()));
    activityDTO.setActivityDetailsList(
        activity.getActivityDetails().stream()
            .map(
                activityDetails ->
                    new ActivityDetailsDTO(
                        activityDetails.getActivityDetailsId(),
                        activityDetails.getRepetitions(),
                        activityDetails.getWeight(),
                        activityDetails.getNumberOfSets()))
            .collect(Collectors.toList()));
    return activityDTO;
  }
}
