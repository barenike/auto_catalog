package com.example.auto_catalog.model.service;

import com.example.auto_catalog.exceptions.AutoAlreadyExistException;
import com.example.auto_catalog.exceptions.NotEnoughDataForStatisticsDumpException;
import com.example.auto_catalog.infrastructure.AutoCreationRequest;
import com.example.auto_catalog.infrastructure.StatisticsResponse;
import com.example.auto_catalog.model.entity.AutoEntity;
import com.example.auto_catalog.model.repository.AutoRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AutoService {
    private final AutoRepository autoRepository;

    public AutoService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    protected static Date getCurrentYekaterinburgDate() {
        DateTimeZone zoneYekaterinburg = DateTimeZone.forID("Asia/Yekaterinburg");
        DateTime now = DateTime.now(zoneYekaterinburg);
        return now.toDate();
    }

    public List<AutoEntity> readAll() {
        return autoRepository.findAll();
    }

    public AutoEntity findByPlateNumber(String plateNumber) {
        return autoRepository.findByPlateNumber(plateNumber);
    }

    public StatisticsResponse getStatistics() {
        StatisticsResponse response = new StatisticsResponse();
        long count = autoRepository.count();
        if (count < 1) {
            throw new NotEnoughDataForStatisticsDumpException("Not enough data for statistics dump.");
        }
        response.setCount(count);
        response.setFirstRecordCreationData(autoRepository.findFirstByOrderByCreationDateAsc().getCreationDate());
        response.setLastRecordCreationData(autoRepository.findFirstByOrderByCreationDateDesc().getCreationDate());
        return response;
    }

    public void create(AutoCreationRequest autoCreationRequest) {
        if (findByPlateNumber(autoCreationRequest.getPlateNumber()) != null) {
            throw new AutoAlreadyExistException("Auto already exist in catalog.");
        }
        AutoEntity auto = new AutoEntity();
        auto.setPlateNumber(autoCreationRequest.getPlateNumber());
        auto.setBrand(autoCreationRequest.getBrand());
        auto.setColor(autoCreationRequest.getColor());
        auto.setManufacturingYear(autoCreationRequest.getManufacturingYear());
        auto.setCreationDate(getCurrentYekaterinburgDate());
        autoRepository.save(auto);
    }

    public boolean delete(UUID id) {
        if (autoRepository.existsById(id)) {
            autoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
