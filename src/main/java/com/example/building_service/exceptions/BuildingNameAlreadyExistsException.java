package com.example.building_service.exceptions;

public class BuildingNameAlreadyExistsException extends RuntimeException {
    public BuildingNameAlreadyExistsException(String message) {
        super(message);
    }
}
