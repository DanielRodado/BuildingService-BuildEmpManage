package com.example.building_service.service;

import com.example.building_service.model.BuildingEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuildingService {

    // Methods repository

    Mono<BuildingEntity> getBuildingById(Long buildingId);

    Flux<BuildingEntity> getAllBuildingsByCapacityBetween(int minCapacity, int maxCapacity);

    Flux<BuildingEntity> getAllBuildings();

    // Methods Controller

}
