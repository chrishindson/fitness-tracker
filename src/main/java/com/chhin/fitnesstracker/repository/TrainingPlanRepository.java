package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.TrainingPlan;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {

  @Query(
      "SELECT a FROM TrainingPlan a WHERE a.ftUser =:user AND a.activityDate = :recordedDate ORDER BY a.activityType.activityTypeDescription")
  List<TrainingPlan> findTrainingPlanByFtUserAndActivityDate(
      @Param("user") FTUser user, @Param("recordedDate") LocalDate recordedDate);
}
