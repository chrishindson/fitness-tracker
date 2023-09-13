package com.chhin.fitnesstracker.service;

import com.chhin.fitnesstracker.entity.BodyMeasurementArea;
import com.chhin.fitnesstracker.repository.BodyMeasurementAreaRepository;
import com.chhin.fitnesstracker.repository.BodyMeasurementRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service public class BodyMeasurementService {
    private final BodyMeasurementRepository bodyMeasurementRepository;
    private final BodyMeasurementAreaRepository bodyMeasurementAreaRepository;

    public BodyMeasurementService(BodyMeasurementRepository bodyMeasurementRepository, BodyMeasurementAreaRepository bodyMeasurementAreaRepository) {
        this.bodyMeasurementRepository = bodyMeasurementRepository;
        this.bodyMeasurementAreaRepository = bodyMeasurementAreaRepository;
    }

    
    public List<BodyMeasurementArea> findAllBodyMeasurementAreas() {
        return bodyMeasurementAreaRepository.findAllBodyMeasurementAreas();
    }
}
