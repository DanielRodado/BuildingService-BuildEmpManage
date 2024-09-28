package com.example.building_service.repository;

import com.example.building_service.model.BuildingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuildingRepository extends ReactiveCrudRepository<BuildingEntity, Long> {

    Mono<Boolean> existsByBuildingName(String buildingName);

    Mono<BuildingEntity> findByBuildingName(String buildingName);

    Flux<BuildingEntity> findByCapacityBetween(int minCapacity, int maxCapacity);

}
