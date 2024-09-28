package com.example.building_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public Mono<ResponseEntity<String>> handleValidationException(ValidationException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()));
    }

    @ExceptionHandler(InvalidBuildingNameException.class)
    public Mono<ResponseEntity<String>> handleInvalidBuildingNameException(InvalidBuildingNameException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage()));
    }

    @ExceptionHandler(InvalidBuildingStatusException.class)
    public Mono<ResponseEntity<String>> handleInvalidBuildingStatusException(InvalidBuildingStatusException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage()));
    }

    @ExceptionHandler(BuildingNameAlreadyExistsException.class)
    public Mono<ResponseEntity<String>> handleBuildingNameAlreadyExistsException(BuildingNameAlreadyExistsException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage()));
    }

    @ExceptionHandler(InvalidBuildingCapacityException.class)
    public Mono<ResponseEntity<String>> handleInvalidBuildingCapacityException(InvalidBuildingCapacityException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage()));
    }

    @ExceptionHandler(BuildingNotFoundException.class)
    public Mono<ResponseEntity<String>> handleBuildingNotFoundException(BuildingNotFoundException ex) {
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage()));
    }

}
