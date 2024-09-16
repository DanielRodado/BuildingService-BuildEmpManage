package com.example.building_service.repository;

import com.example.building_service.model.BuildingEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BuildingRepository extends ReactiveCrudRepository<BuildingEntity, Long> {

    Flux<BuildingEntity> findByCapacityBetween(int minCapacity, int maxCapacity);

}
