package com.example.building_service.service;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.model.BuildingEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuildingService {

    // Methods repository

    Mono<BuildingEntity> getBuildingById(Long buildingId);

    Mono<BuildingEntity> getBuildingByBuildingName(String buildingName);

    Flux<BuildingEntity> getAllBuildingsByCapacityBetween(int minCapacity, int maxCapacity);

    Flux<BuildingEntity> getAllBuildings();

    Mono<Boolean> existsBuildingByBuildingName(String buildingName);

    Mono<BuildingEntity> saveBuilding(BuildingEntity building);

    // Validations

    Mono<BuildingApplicationDTO> validateBuildingApp(BuildingApplicationDTO buildingApp);

    Mono<BuildingApplicationDTO> validateExistsBuildingName(String buildingName, BuildingApplicationDTO buildingApp);

    // Methods Controller

    Mono<Void> createBuilding(Mono<BuildingApplicationDTO> buildingAppMono);

    Mono<Void> deleteBuilding(String buildingName);

    // Set Available Capacity from Building Where Assign or Remove Employee To Building
    Mono<Void> assignEmployeeToBuilding(String buildingName);

    Mono<BuildingEntity> decreaseAvailableCapacity(BuildingEntity building);

    Mono<Void> removeEmployeeFromBuilding(String buildingName);

    Mono<BuildingEntity> increaseAvailableCapacity(BuildingEntity building);

}
