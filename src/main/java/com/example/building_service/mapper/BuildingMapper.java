package com.example.building_service.mapper;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.enums.BuildingStatus;
import com.example.building_service.model.BuildingEntity;

public final class BuildingMapper {

    // from App DTO to Entity
    public static BuildingEntity toBuildingEntity(BuildingApplicationDTO buildingApp) {
        return new BuildingEntity(buildingApp.name().toUpperCase(), BuildingStatus.valueOf(buildingApp.status().toUpperCase()),
                buildingApp.capacity());
    }

}
