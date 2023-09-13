package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.Activity;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.model.ActivitySummaryDTO;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

  @Query("SELECT u.username as username, a.activityDate as activityDate, "
      + "count(a.activityId) as activityCount, "
      + "sum(a.calorieCount) as totalCalories, "
      + "sum(a.timeTaken) as totalTime "
      + "FROM Activity a, FTUser u "
      + "WHERE lower(u.username) = lower(:username) "
      + "GROUP BY u.username, a.activityDate ")
  Page<ActivitySummaryDTO> getActivitySummaryByFtUser(@Param("username") String username,
                                                      @Param("pageable") Pageable pageable);

  @Query(value =
      "SELECT u.username, a.activityDate, "
          + "sum(a.distance) as totalDistance, count(1) as activityCount, "
          + "sum(a.calorieCount) as totalCalories, "
          + "sum(a.timeTaken) as totalTime "
          + "FROM Activity a, FTUser u "
          + "WHERE a.ftUser = :user "
          + "AND a.activityDate = :activityDate "
          + "GROUP BY u.username, a.activityDate ")
  ActivitySummaryDTO getActivitySummaryByFtUserAndActivityDate(@Param("user") FTUser user,
                                                               @Param("activityDate") LocalDate activityDate);


  @Query("SELECT a FROM Activity a "
      + "WHERE lower(a.ftUser.username) = lower(:username) ")
  Page<Activity> getActivitiesByFtUser(@Param("username") String username,
                                       @Param("pageable") Pageable pageable);

  @Query("SELECT a from Activity a WHERE a.activityDate = :activityDate AND a.ftUser = :user " +
      "ORDER BY a.startTime, a.activityType.activityTypeDescription")
  List<Activity> findByActivityDateAndUser(@Param("activityDate") LocalDate activityDate,
                                           @Param("user") FTUser user);

}
