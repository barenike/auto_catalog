package com.example.auto_catalog.model.repository;

import com.example.auto_catalog.model.entity.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutoRepository extends JpaRepository<AutoEntity, UUID> {
    AutoEntity findByPlateNumber(String plateNumber);

    AutoEntity findFirstByOrderByCreationDateDesc();

    AutoEntity findFirstByOrderByCreationDateAsc();
}
