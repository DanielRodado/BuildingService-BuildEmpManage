package com.example.building_service.exceptions;

public class InvalidBuildingCapacityException extends RuntimeException {
    public InvalidBuildingCapacityException(String message) {
        super(message);
    }
}
