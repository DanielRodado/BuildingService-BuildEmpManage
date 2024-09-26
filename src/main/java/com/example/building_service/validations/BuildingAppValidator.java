package com.example.building_service.validations;

import com.example.building_service.dto.BuildingApplicationDTO;
import com.example.building_service.enums.BuildingNameType;
import com.example.building_service.enums.BuildingStatus;
import com.example.building_service.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BuildingAppValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return BuildingApplicationDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        BuildingApplicationDTO buildingApp = (BuildingApplicationDTO) target;
        validateBuildingName(buildingApp.name().toUpperCase());
        validateBuildingStatus(buildingApp.status().toUpperCase());
        validateBuildingCapacity(buildingApp.capacity());
    }

    private void validateBuildingName(String buildingName) {
        if (buildingName == null || buildingName.isBlank() || verifyBuildingName(buildingName)) {
            throw new ValidationException("Name of building is invalid.");
        }
    }

    private void validateBuildingStatus(String buildingStatus) {
        if (buildingStatus == null || buildingStatus.isBlank() || verifyBuildingStatus(buildingStatus)) {
            throw new ValidationException("Status of building is invalid.");
        }
    }

    private void validateBuildingCapacity(int capacity) {
        if (capacity < 0) {
            throw new ValidationException("Capacity of building is invalid.");
        }
    }

    public static boolean verifyBuildingName(String buildingName) {
        try {
            return BuildingNameType.valueOf("B"+buildingName).getValue().isBlank();
        } catch (IllegalArgumentException ignore){
            return true;
        }
    }

    public static boolean verifyBuildingStatus(String buildingStatus) {
        try {
            return BuildingStatus.valueOf(buildingStatus).toString().isBlank();
        } catch (IllegalArgumentException ignore){
            return true;
        }
    }


}
