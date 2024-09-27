package com.example.building_service.service.implement;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.enums.BuildingStatus;
import com.example.building_service.exceptions.BuildingNameAlreadyExistsException;
import com.example.building_service.exceptions.BuildingNotFoundException;
import com.example.building_service.exceptions.ValidationException;
import com.example.building_service.mapper.BuildingMapper;
import com.example.building_service.model.BuildingEntity;
import com.example.building_service.repository.BuildingRepository;
import com.example.building_service.service.BuildingService;
import com.example.building_service.validations.BuildingAppValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.building_service.utils.Messages.*;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingAppValidator buildingAppValidator;

    // Methods repository

    @Override
    public Mono<BuildingEntity> getBuildingById(Long buildingId) {
        return buildingRepository
                .findById(buildingId)
                .switchIfEmpty(Mono.error(new BuildingNotFoundException(BUILDING_NOT_FOUND)));
    }

    @Override
    public Mono<BuildingEntity> getBuildingByBuildingName(String buildingName) {
        return buildingRepository
                .findByBuildingName(buildingName)
                .switchIfEmpty(Mono.error(new BuildingNotFoundException(BUILDING_NOT_FOUND)));
    }

    @Override
    public Flux<BuildingEntity> getAllBuildingsByCapacityBetween(int minCapacity, int maxCapacity) {
        return buildingRepository.findByCapacityBetween(minCapacity, maxCapacity);
    }

    @Override
    public Flux<BuildingEntity> getAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public Mono<Boolean> existsBuildingByBuildingName(String buildingName) {
        return buildingRepository.existsByBuildingName(buildingName);
    }

    @Override
    public Mono<BuildingEntity> saveBuilding(BuildingEntity building) {
        return buildingRepository.save(building);
    }

    // Validations

    @SuppressWarnings("DataFlowIssue")
    @Override
    public Mono<BuildingApplicationDTO> validateBuildingApp(BuildingApplicationDTO buildingApp) {
        buildingAppValidator.validate(buildingApp, null);
        return Mono.just(buildingApp);
    }

    @Override
    public Mono<BuildingApplicationDTO> validateExistsBuildingName(String buildingName, BuildingApplicationDTO buildingApp) {
        return existsBuildingByBuildingName(buildingName)
                .filter(exists -> !exists)
                .switchIfEmpty(Mono.error(new BuildingNameAlreadyExistsException(BUILDING_EXISTS)))
                .thenReturn(buildingApp);
    }

    @Override
    public Mono<Void> validateStatus(String status) {
        try {
            BuildingStatus.valueOf(status);
            return Mono.empty();
        } catch (IllegalArgumentException e) {
            return Mono.error(new ValidationException(STATUS_INVALID));
        }
    }

    // Methods Controller

    @Override
    public Mono<Void> createBuilding(Mono<BuildingApplicationDTO> buildingAppMono) {
        return buildingAppMono
                .flatMap(this::validateBuildingApp)
                .flatMap(buildingApp -> validateExistsBuildingName(buildingApp.name(), buildingApp))
                .map(BuildingMapper::toBuildingEntity)
                .flatMap(this::saveBuilding)
                .then();
    }

    @Override
    public Mono<Void> deleteBuilding(String buildingName) {
        return getBuildingByBuildingName(buildingName).flatMap(buildingRepository::delete);
    }

    @Override
    public Mono<Void> requestExistsBuilding(String buildingName) {
        return existsBuildingByBuildingName(buildingName)
                .filter(exists -> exists)
                .switchIfEmpty(Mono.error(new BuildingNotFoundException(BUILDING_NOT_FOUND)))
                .then();
    }

    @Override
    public Mono<Void> changeStatusOfBuilding(String buildingName, String status) {
        return validateStatus(status)
                .then(getBuildingByBuildingName(buildingName))
                .flatMap(building -> changeStatus(building, status))
                .flatMap(this::saveBuilding)
                .then();
    }

    @Override
    public Mono<BuildingEntity> changeStatus(BuildingEntity building, String status) {
        building.setBuildingStatus(BuildingStatus.valueOf(status));
        return Mono.just(building);
    }

    // Set Available Capacity from Building Where Assign or Remove Employee To Building
    @Override
    public Mono<Void> assignEmployeeToBuilding(String buildingName) {
        return getBuildingByBuildingName(buildingName)
                .flatMap(this::decreaseAvailableCapacity)
                .flatMap(this::saveBuilding)
                .then();
    }

    @Override
    public Mono<BuildingEntity> decreaseAvailableCapacity(BuildingEntity building) {
        building.setAvailableCapacity(building.getAvailableCapacity()-1);
        return Mono.just(building);
    }

    @Override
    public Mono<Void> removeEmployeeFromBuilding(String buildingName) {
        return getBuildingByBuildingName(buildingName)
                .flatMap(this::increaseAvailableCapacity)
                .flatMap(this::saveBuilding)
                .then();
    }

    @Override
    public Mono<BuildingEntity> increaseAvailableCapacity(BuildingEntity building) {
        building.setAvailableCapacity(building.getAvailableCapacity()+1);
        return Mono.just(building);
    }
}
