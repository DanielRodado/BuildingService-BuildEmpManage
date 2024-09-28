package com.example.building_service.service;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.model.BuildingEntity;
import reactor.core.publisher.Mono;

public interface BuildingValidationService {

    // Validate entity existence

    Mono<Boolean> existsBuildingByBuildingName(String buildingName);

    Mono<Void> validateExistsBuildingName(String buildingName);

    // Validate Application DTO

    Mono<BuildingApplicationDTO> validateBuildingApp(BuildingApplicationDTO buildingApp);

    // Validate properties

    Mono<Void> validateBuildingStatus(String status);

    Mono<Void> validateBuildingCapacity(BuildingEntity building, int capacity);

    Mono<Void> validateAvailableCapacityBeforeAssigning(BuildingEntity building);

    Mono<Void> validateAvailableCapacityBeforeUnassigning(BuildingEntity building);

}
