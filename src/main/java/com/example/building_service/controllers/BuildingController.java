package com.example.building_service.controllers;

import com.example.building_service.model.BuildingEntity;
import com.example.building_service.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/capacity/between")
    public Flux<BuildingEntity> getAllBuildingsIfCapacityBetweenIn(@RequestParam(defaultValue = "0") int minCapacity,
                                                                   @RequestParam(defaultValue = "200") int maxCapacity) {
        return buildingService.getAllBuildingsByCapacityBetween(minCapacity, maxCapacity);
    }

    @PatchMapping("/{buildingName}/assign")
    public Mono<ResponseEntity<String>> assignEmployeeToBuilding(@PathVariable String buildingName) {
        return buildingService
                .assignEmployeeToBuilding(buildingName)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @PatchMapping("/{buildingName}/remove")
    public Mono<ResponseEntity<String>> removeEmployeeFromBuilding(@PathVariable String buildingName) {
        return buildingService
                .removeEmployeeFromBuilding(buildingName)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

}
