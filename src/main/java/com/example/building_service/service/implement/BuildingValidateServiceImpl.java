package com.example.building_service.service.implement;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.enums.BuildingStatus;
import com.example.building_service.exceptions.BuildingNameAlreadyExistsException;
import com.example.building_service.exceptions.InvalidBuildingCapacityException;
import com.example.building_service.exceptions.ValidationException;
import com.example.building_service.model.BuildingEntity;
import com.example.building_service.repository.BuildingRepository;
import com.example.building_service.service.BuildingValidationService;
import com.example.building_service.validations.BuildingAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import static com.example.building_service.utils.Messages.*;

public class BuildingValidateServiceImpl implements BuildingValidationService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingAppValidator buildingAppValidator;

    @Override
    public Mono<Boolean> existsBuildingByBuildingName(String buildingName) {
        return buildingRepository.existsByBuildingName(buildingName);
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public Mono<BuildingApplicationDTO> validateBuildingApp(BuildingApplicationDTO buildingApp) {
        buildingAppValidator.validate(buildingApp, null);
        return Mono.just(buildingApp);
    }

    @Override
    public Mono<Void> validateExistsBuildingName(String buildingName) {
        return existsBuildingByBuildingName(buildingName)
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new BuildingNameAlreadyExistsException(BUILDING_EXISTS)))
                .then();
    }

    @Override
    public Mono<Void> validateBuildingStatus(String status) {
        try {
            BuildingStatus.valueOf(status);
            return Mono.empty();
        } catch (IllegalArgumentException e) {
            return Mono.error(new ValidationException(STATUS_INVALID));
        }
    }

    @Override
    public Mono<Void> validateBuildingCapacity(BuildingEntity building, int capacity) {
        return building.getNumberOfEmployees() > capacity
                ? Mono.error(new InvalidBuildingCapacityException(CAPACITY_INVALID + building.getNumberOfEmployees()))
                : Mono.empty();
    }

}
