package com.example.building_service.utils;

import com.example.building_service.enums.BuildingNameType;
import com.example.building_service.exceptions.InvalidBuildingNameException;

public final class BuildingUtil {

    public static String toBuildingName(String buildingName) {
        try {
            return BuildingNameType.valueOf("B"+buildingName.toUpperCase()).getValue();
        } catch (Error ignore){
            throw new InvalidBuildingNameException("Name of building is invalid.");
        }
    }

}
