package com.example.building_service.controllers;

import com.example.building_service.model.BuildingEntity;
import com.example.building_service.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/{buildingId}")
    public Mono<BuildingEntity> getBuildingById(@PathVariable Long buildingId) {
        return buildingService.getBuildingById(buildingId);
    }

    @GetMapping
    public Flux<BuildingEntity> getAllBuildings() {
        return buildingService.getAllBuildings();
    }

}
