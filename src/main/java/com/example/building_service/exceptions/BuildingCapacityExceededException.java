package com.example.building_service.exceptions;

public class BuildingCapacityExceededException extends RuntimeException {
    public BuildingCapacityExceededException(String message) {
        super(message);
    }
}
