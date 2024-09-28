package com.example.building_service.service;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.model.BuildingEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuildingService {

    // Methods repository

    Mono<BuildingEntity> getBuildingById(Long buildingId);

    Mono<BuildingEntity> getBuildingByBuildingName(String buildingName);

    Flux<BuildingEntity> getAllBuildingsWithCapacityBetween(int minCapacity, int maxCapacity);

    Flux<BuildingEntity> getAllBuildingsWithCapacityAvailable();

    Flux<BuildingEntity> getAllBuildings();

    Mono<BuildingEntity> saveBuilding(BuildingEntity building);

    // Methods Controller

    Mono<Void> createBuilding(Mono<BuildingApplicationDTO> buildingAppMono);

    Mono<Void> deleteBuilding(String buildingName);

    Mono<Void> requestExistsBuilding(String buildingName);

    Mono<Void> changeStatusOfBuilding(String buildingName, String status);

    Mono<BuildingEntity> changeStatus(BuildingEntity building, String status);

    Mono<Void> changeCapacityOfBuilding(String buildingName, int capacity);

    Mono<BuildingEntity> changeCapacity(BuildingEntity building, int capacity);

    // Set Available Capacity from Building Where Assign or Remove Employee To Building
    Mono<Void> assignEmployeeToBuilding(String buildingName);

    Mono<BuildingEntity> decreaseAvailableCapacity(BuildingEntity building);

    Mono<Void> removeEmployeeFromBuilding(String buildingName);

    Mono<BuildingEntity> increaseAvailableCapacity(BuildingEntity building);

}
