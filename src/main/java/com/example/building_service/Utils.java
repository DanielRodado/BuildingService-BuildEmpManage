package com.example.building_service;

import com.example.building_service.repository.BuildingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {

    @Bean
    public CommandLineRunner initData(BuildingRepository buildingRepository) {
        return args -> {
            /*BuildingEntity building1A = new BuildingEntity(toBuildingName("1A"), BuildingStatus.ACTIVE, 5);
            buildingRepository.save(building1A).subscribe();

            BuildingEntity building2B = new BuildingEntity(toBuildingName("2B"), BuildingStatus.ACTIVE, 20);
            buildingRepository.save(building2B).subscribe();

            BuildingEntity building3C = new BuildingEntity(toBuildingName("3C"), BuildingStatus.UNDER_RENOVATION, 50);
            buildingRepository.save(building3C).subscribe();

            BuildingEntity building4D = new BuildingEntity(toBuildingName("4D"), BuildingStatus.ACTIVE, 80);
            buildingRepository.save(building4D).subscribe();

            BuildingEntity building5E = new BuildingEntity(toBuildingName("5E"), BuildingStatus.UNDER_CONSTRUCTION, 100);
            buildingRepository.save(building5E).subscribe();*/
        };
    }

}
