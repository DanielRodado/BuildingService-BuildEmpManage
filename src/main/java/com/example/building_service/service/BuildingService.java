package com.example.building_service.service;

import com.example.building_service.model.BuildingEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuildingService {

    // Methods repository

    Mono<BuildingEntity> getBuildingById(Long buildingId);

    Mono<BuildingEntity> getBuildingByBuildingName(String buildingName);

    Flux<BuildingEntity> getAllBuildingsByCapacityBetween(int minCapacity, int maxCapacity);

    Flux<BuildingEntity> getAllBuildings();

    Mono<BuildingEntity> saveBuilding(BuildingEntity building);

    // Methods Controller

    // Set Available Capacity from Building Where Assign or Remove Employee To Building
    Mono<Void> assignEmployeeToBuilding(String buildingName);

    Mono<BuildingEntity> increaseAvailableCapacity(BuildingEntity building);

    Mono<Void> removeEmployeeFromBuilding(String buildingName);

    Mono<BuildingEntity> decreaseAvailableCapacity(BuildingEntity building);

}
