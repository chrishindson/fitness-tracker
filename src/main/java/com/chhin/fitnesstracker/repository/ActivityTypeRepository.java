package com.chhin.fitnesstracker.repository;

import com.chhin.fitnesstracker.entity.ActivityType;
import com.chhin.fitnesstracker.model.SelectOptionsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {

  @Query(
      "SELECT new com.chhin.fitnesstracker.model.SelectOptionsDTO(a.activityTypeId, a.activityTypeDescription) "
          + "FROM ActivityType a ORDER BY a.activityTypeDescription")
  List<SelectOptionsDTO> getActivityTypeList();

  @Query("SELECT a FROM ActivityType a WHERE a.activityTypeDescription = :activityType")
  ActivityType findByActivityTypeDescription(
      @Param("activityType") String activityType);
}
