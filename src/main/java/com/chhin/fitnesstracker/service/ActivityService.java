package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entities.Activity;
import com.chhin.fitnesstracker.entities.ActivityDetails;
import com.chhin.fitnesstracker.entities.ActivityType;
import com.chhin.fitnesstracker.entities.FTUser;
import com.chhin.fitnesstracker.model.*;
import com.chhin.fitnesstracker.repository.ActivityDetailsRepository;
import com.chhin.fitnesstracker.repository.ActivityRepository;
import com.chhin.fitnesstracker.repository.ActivityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ActivityService {

  @Autowired
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  private final ActivityRepository activityRepository;
  private final ActivityTypeRepository activityTypeRepository;
  @Autowired
  private ActivityDetailsRepository activityDetailsRepository;

  public ActivityService(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                         ActivityRepository activityRepository,
                         ActivityTypeRepository activityTypeRepository) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    this.activityRepository = activityRepository;
    this.activityTypeRepository = activityTypeRepository;
  }

  public void saveActivity(ActivityDTO dto, FTUser user) {
    Activity activity = new Activity();
    ActivityType activityType = activityTypeRepository.findById(Long.valueOf(dto.getActivityType()))
        .orElse(null);
    activity.setActivityType(activityType);
    activity.setActivityDate(dto.getActivityDate().toLocalDate());
    activity.setDistance(dto.getDistance() != null ? dto.getDistance() : null);
    activity.setStartTime(dto.getStartTime().toLocalTime());
    activity.setNotes(dto.getNotes() != null ? dto.getNotes() : null);
    activity.setCalorieCount(dto.getCalorieCount());
    activity.setTimeTaken(dto.getTimeTaken().toLocalTime());
    activity.setFtUser(user);
    Activity savedActivity = activityRepository.save(activity);

    if (dto.getActivityDetailsList().size() > 0) {
      for (ActivityDetailsDTO detailsDTO : dto.getActivityDetailsList()) {
        ActivityDetails details = new ActivityDetails();
        details.setActivity(savedActivity);
        details.setRepetitions(detailsDTO.getRepetitions());
        details.setNumberOfSets(detailsDTO.getNumberOfSets());
        if (detailsDTO.getWeight() != null
            && detailsDTO.getWeight().compareTo(BigDecimal.ZERO) == 0) {
          details.setWeight(detailsDTO.getWeight());
        }
        activityDetailsRepository.save(details);
      }
    }

  }


  public List<Activity> findByActivityDateAndUser(LocalDate activityDate, FTUser user) {
    return activityRepository.findByActivityDateAndUser(activityDate, user);
  }

  public List<SelectOptionsDTO> getActivityTypeList() {
    return activityTypeRepository.getActivityTypeList();
  }

  public Page<Activity> getActivitiesByFtUser(String username, Pageable pageable) {
    return activityRepository.getActivitiesByFtUser(username, pageable);
  }

  public Page<ActivitySummaryDTO> getActivitySummaryByFtUser(String username, Pageable pageable) {
    return activityRepository.getActivitySummaryByFtUser(username, pageable);
  }

//  public List<ActivitySummaryDTO> getActivitySummaryByFtUserList(String username) {
//    return activityRepository.getActivitySummaryByFtUserList(username);
//  }

  public Page<ActivitySummaryDTO> getActivitySummaryByFtUserListJdbc(String username,
                                                                     Pageable pageable) {
    String sql =
        "SELECT u.username, a.activity_date as activityDate, "
            + "count(1) as activityCount, "
            + "sum(coalesce(a.distance,0)) as totalDistance, "
            + "sum(a.calorie_count) as totalCalories, "
            + "sum(a.time_taken) as totalTime "
            + "FROM activity a, users u "
            + "WHERE lower(u.username) = lower(:username) "
            + "group by u.username, a.activity_date "
            + "ORDER BY a.activity_date desc ";
    Map<String, Object> params = new HashMap<>();
    params.put("username", username);
    List<ActivitySummaryDTO> list = namedParameterJdbcTemplate.query(sql, params,
        new BeanPropertyRowMapper<>(ActivitySummaryDTO.class));
    final int start = (int) pageable.getOffset();
    final int end = Math.min((start + pageable.getPageSize()), list.size());
    return new PageImpl<>(list.subList(start, end), pageable, list.size());
  }

  public List<ActivityDiaryDTO> getDiaryFtUserListJdbc(String username,
                                                       LocalDate diaryMonth) {
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
    params.put("username", username);
    params.put("diaryMonth", diaryMonth);
    return namedParameterJdbcTemplate.query(sql, params,
        new BeanPropertyRowMapper<>(ActivityDiaryDTO.class));
  }

  public ActivityHistoryDTO getAllTimeActivitySummaryByFtUserListJdbc(String username) {
    String sql =
        "SELECT u.username, "
            + "count(1) as activityCount, "
            + "sum(coalesce(a.distance,0)) as totalDistance, "
            + "sum(a.calorie_count) as totalCalories, "
            + "sum(a.time_taken) as totalTime "
            + "FROM activity a, users u "
            + "WHERE lower(u.username) = lower(:username) "
            + "group by u.username";
    Map<String, Object> params = new HashMap<>();
    params.put("username", username);
    List<ActivityHistoryDTO> list = namedParameterJdbcTemplate.query(sql, params,
        new BeanPropertyRowMapper<>(ActivityHistoryDTO.class));
    return list.get(0);
  }


  public Activity findByActivityId(Long activityId) {
    return activityRepository.findById(activityId).orElse(null);
  }
}
