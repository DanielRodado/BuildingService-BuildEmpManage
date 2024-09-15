package com.example.building_service.repository;

import com.example.building_service.model.BuildingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BuildingRepository extends ReactiveCrudRepository<BuildingEntity, Long> {
}
