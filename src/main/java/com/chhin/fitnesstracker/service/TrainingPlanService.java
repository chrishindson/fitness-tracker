package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.FTRuntimeException;
import com.chhin.fitnesstracker.entity.ActivityType;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.entity.TrainingPlan;
import com.chhin.fitnesstracker.model.TrainingPlanDTO;
import com.chhin.fitnesstracker.repository.ActivityTypeRepository;
import com.chhin.fitnesstracker.repository.TrainingPlanRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TrainingPlanService {
  private final TrainingPlanRepository trainingPlanRepository;
  private final ActivityTypeRepository activityTypeRepository;

  public TrainingPlanService(
      TrainingPlanRepository trainingPlanRepository,
      ActivityTypeRepository activityTypeRepository) {
    this.trainingPlanRepository = trainingPlanRepository;
    this.activityTypeRepository = activityTypeRepository;
  }

  public void saveTrainingPlan(TrainingPlanDTO trainingPlanDTO, FTUser user) {
    List<TrainingPlan> trainingPlanList = new ArrayList<>();
    LocalDate startDate = trainingPlanDTO.getStartDate().toLocalDate();
    LocalDate endDate = startDate;
    ActivityType activityType =
        activityTypeRepository
            .findById(Long.valueOf(trainingPlanDTO.getActivityType()))
            .orElseThrow(FTRuntimeException::new);
    if (trainingPlanDTO.getEndDate() != null) {
      endDate = trainingPlanDTO.getEndDate().toLocalDate();
    }

    for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
      boolean createRecord = isCreateRecord(trainingPlanDTO, date);
      if (Boolean.TRUE.equals(createRecord)) {
        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setPlannedTime(trainingPlanDTO.getPlannedTime().toLocalTime());
        trainingPlan.setDistance(trainingPlanDTO.getDistance());
        trainingPlan.setNumberOfSets(trainingPlanDTO.getNumberOfSets());
        trainingPlan.setRepetitions(trainingPlanDTO.getRepetitions());
        trainingPlan.setWeight(trainingPlan.getWeight());
        trainingPlan.setFtUser(user);
        trainingPlan.setActivityType(activityType);
        trainingPlan.setActivityDate(date);
        trainingPlan.setNotes(trainingPlan.getNotes());
        trainingPlanList.add(trainingPlan);
      }
    }
    trainingPlanRepository.saveAll(trainingPlanList);
  }

  private static boolean isCreateRecord(TrainingPlanDTO trainingPlanDTO, LocalDate date) {
    boolean createRecord = false;
    switch (date.getDayOfWeek()) {
      case MONDAY -> {
        if (trainingPlanDTO.isLogMonday()) {
          createRecord = true;
        }
      }
      case TUESDAY -> {
        if (trainingPlanDTO.isLogTuesday()) {
          createRecord = true;
        }
      }
      case WEDNESDAY -> {
        if (trainingPlanDTO.isLogWednesday()) {
          createRecord = true;
        }
      }
      case THURSDAY -> {
        if (trainingPlanDTO.isLogThursday()) {
          createRecord = true;
        }
      }
      case FRIDAY -> {
        if (trainingPlanDTO.isLogFriday()) {
          createRecord = true;
        }
      }
      case SATURDAY -> {
        if (trainingPlanDTO.isLogSaturday()) {
          createRecord = true;
        }
      }
      case SUNDAY -> {
        if (trainingPlanDTO.isLogSunday()) {
          createRecord = true;
        }
      }
    }
    return createRecord;
  }
}
