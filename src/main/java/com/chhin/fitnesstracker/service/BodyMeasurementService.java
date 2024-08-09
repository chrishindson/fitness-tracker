package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.BodyMeasurement;
import com.chhin.fitnesstracker.entity.BodyMeasurementArea;
import com.chhin.fitnesstracker.entity.FTUser;
import com.chhin.fitnesstracker.model.BodyMeasurementDTO;
import com.chhin.fitnesstracker.repository.BodyMeasurementAreaRepository;
import com.chhin.fitnesstracker.repository.BodyMeasurementRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BodyMeasurementService {
  private final BodyMeasurementRepository bodyMeasurementRepository;
  private final BodyMeasurementAreaRepository bodyMeasurementAreaRepository;

  public BodyMeasurementService(
      BodyMeasurementRepository bodyMeasurementRepository,
      BodyMeasurementAreaRepository bodyMeasurementAreaRepository) {
    this.bodyMeasurementRepository = bodyMeasurementRepository;
    this.bodyMeasurementAreaRepository = bodyMeasurementAreaRepository;
  }

  public List<BodyMeasurementArea> findAllBodyMeasurementAreas() {
    return bodyMeasurementAreaRepository.findAllBodyMeasurementAreas();
  }

  public void saveBodyMeasurement(BodyMeasurementDTO dto, FTUser ftUser) {
    BodyMeasurementArea bodyMeasurementArea =
        bodyMeasurementAreaRepository.findAllBodyMeasurementAreas().stream()
            .filter(x -> x.getBodyMeasurementAreaId().equals(dto.getBodyMeasurementArea()))
            .findFirst()
            .orElseThrow(RuntimeException::new);

    BodyMeasurement bodyMeasurement =
        BodyMeasurement.builder()
            .bodyMeasurementArea(bodyMeasurementArea)
            .ftUser(ftUser)
            .measurementDate(dto.getMeasurementDate().toLocalDate())
            .measurementSize(dto.getMeasurementSize())
            .build();
    bodyMeasurementRepository.save(bodyMeasurement);
  }
}
