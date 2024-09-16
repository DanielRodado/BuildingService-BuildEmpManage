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
    public Flux<BuildingEntity> getAllBuildingsByCapacityBetween(int minCapacity, int maxCapacity) {
        return buildingRepository.findByCapacityBetween(minCapacity, maxCapacity);
    }

    @Override
    public Flux<BuildingEntity> getAllBuildings() {
        return buildingRepository.findAll();
    }
}
