package com.example.building_service.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public Mono<ResponseEntity<String>> handleValidationException(ValidationException ex) {
        return Mono.just(ResponseEntity.status(400).body(ex.getMessage()));
    }

    @ExceptionHandler(BuildingNameAlreadyExistsException.class)
    public Mono<ResponseEntity<String>> handleBuildingNameAlreadyExistsException(BuildingNameAlreadyExistsException ex) {
        return Mono.just(ResponseEntity.status(403).body(ex.getMessage()));
    }

    @ExceptionHandler(InvalidBuildingCapacityException.class)
    public Mono<ResponseEntity<String>> handleInvalidBuildingCapacityException(InvalidBuildingCapacityException ex) {
        return Mono.just(ResponseEntity.status(403).body(ex.getMessage()));
    }

    @ExceptionHandler(BuildingCapacityExceededException.class)
    public Mono<ResponseEntity<String>> handleBuildingCapacityExceededException(BuildingCapacityExceededException ex) {
        return Mono.just(ResponseEntity.status(403).body(ex.getMessage()));
    }

    @ExceptionHandler(BuildingNotFoundException.class)
    public Mono<ResponseEntity<String>> handleBuildingNotFoundException(BuildingNotFoundException ex) {
        return Mono.just(ResponseEntity.status(404).body(ex.getMessage()));
    }

}
