package com.example.building_service.service.implement;

import com.example.building_service.exceptions.BuildingNotFoundException;
import com.example.building_service.model.BuildingEntity;
import com.example.building_service.repository.BuildingRepository;
import com.example.building_service.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    // Methods repository

    @Override
    public Mono<BuildingEntity> getBuildingById(Long buildingId) {
        return buildingRepository
                .findById(buildingId)
                .switchIfEmpty(Mono.error(new BuildingNotFoundException("The building with id '" + buildingId + "' was not found.")));
    }

    @Override
    public Mono<BuildingEntity> getBuildingByBuildingName(String buildingName) {
        return buildingRepository
                .findByBuildingName(buildingName)
                .switchIfEmpty(Mono.error(new BuildingNotFoundException("The building with name \"" + buildingName + "\" was not found.")));
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
    public Mono<BuildingEntity> saveBuilding(BuildingEntity building) {
        return buildingRepository.save(building);
    }

    // Methods Controller

    // Set Available Capacity from Building Where Assign or Remove Employee To Building
    @Override
    public Mono<Void> assignEmployeeToBuilding(String buildingName) {
        return getBuildingByBuildingName(buildingName)
                .flatMap(this::increaseAvailableCapacity)
                .flatMap(this::saveBuilding)
                .then();
    }

    @Override
    public Mono<BuildingEntity> increaseAvailableCapacity(BuildingEntity building) {
        building.setAvailableCapacity(building.getAvailableCapacity()-1);
        return Mono.just(building);
    }

    @Override
    public Mono<Void> removeEmployeeFromBuilding(String buildingName) {
        return getBuildingByBuildingName(buildingName)
                .flatMap(this::decreaseAvailableCapacity)
                .flatMap(this::saveBuilding)
                .then();
    }

    @Override
    public Mono<BuildingEntity> decreaseAvailableCapacity(BuildingEntity building) {
        building.setAvailableCapacity(building.getAvailableCapacity()+1);
        return Mono.just(building);
    }
}
