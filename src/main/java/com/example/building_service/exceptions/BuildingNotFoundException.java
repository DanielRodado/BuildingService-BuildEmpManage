package com.example.building_service.exceptions;

public class BuildingNotFoundException extends RuntimeException {

    public BuildingNotFoundException(String message) {
        super(message);
    }

}
