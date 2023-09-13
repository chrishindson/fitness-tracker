package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.ActivityType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {

  @Query(
      "SELECT a FROM ActivityType a ORDER BY a.activityTypeDescription")
  List<ActivityType> getActivityTypeList();

  @Query("SELECT a FROM ActivityType a WHERE a.activityTypeDescription = :activityType")
  ActivityType findByActivityTypeDescription(
      @Param("activityType") String activityType);
}
