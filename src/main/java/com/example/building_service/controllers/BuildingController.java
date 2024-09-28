package com.example.building_service.controllers;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.model.BuildingEntity;
import com.example.building_service.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

import static com.example.building_service.utils.Messages.CAPACITY_CHANGED;
import static com.example.building_service.utils.Messages.STATUS_CHANGED;

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
    public Flux<BuildingEntity> getAllBuildingsIfCapacityBetweenIn(@RequestParam(defaultValue = "0", name = "min") int minCapacity,
                                                                   @RequestParam(defaultValue = "200", name = "max") int maxCapacity) {
        return buildingService.getAllBuildingsByCapacityBetween(minCapacity, maxCapacity);
    }

    @GetMapping("/exists/{buildingName}")
    public Mono<ResponseEntity<String>> existsBuildingByBuildingName(@PathVariable String buildingName) {
        return buildingService.requestExistsBuilding(buildingName.toUpperCase()).thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping
    private Mono<ResponseEntity<String>> createBuilding(@RequestBody Mono<BuildingApplicationDTO> buildingApplMono) {
        return buildingService
                .createBuilding(buildingApplMono)
                .thenReturn(ResponseEntity.status(201).body("Building created."));
    }

    @PatchMapping("/{buildingName}/status")
    public Mono<ResponseEntity<String>> changeStatusOfBuilding(@PathVariable String buildingName, @RequestParam String status) {
        return buildingService
                .changeStatusOfBuilding(buildingName.toUpperCase(), status)
                .thenReturn(ResponseEntity.ok(STATUS_CHANGED));
    }

    @PatchMapping("/{buildingName}/capacity")
    public Mono<ResponseEntity<String>> changeCapacityOfBuilding(@PathVariable String buildingName, @RequestParam int capacity) {
        return buildingService
                .changeCapacityOfBuilding(buildingName.toUpperCase(), capacity)
                .thenReturn(ResponseEntity.ok(CAPACITY_CHANGED));
    }

    @PatchMapping("/{buildingName}/employee/assign")
    public Mono<ResponseEntity<String>> assignEmployeeToBuilding(@PathVariable String buildingName) {
        return buildingService
                .assignEmployeeToBuilding(buildingName)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @PatchMapping("/{buildingName}/employee/remove")
    public Mono<ResponseEntity<String>> removeEmployeeFromBuilding(@PathVariable String buildingName) {
        return buildingService
                .removeEmployeeFromBuilding(buildingName.toUpperCase())
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    @DeleteMapping("/{buildingName}")
    public Mono<ResponseEntity<String>> deleteBuilding(@PathVariable String buildingName) {
        return buildingService
                .deleteBuilding(buildingName.toUpperCase())
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

}
