package com.wladi.daily.infrastructure.exceptions.phases;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wladi.daily.application.usecases.phases.exceptions.PhaseNotFoundException;

@RestControllerAdvice
public class PhaseExceptionHandler {

    @ExceptionHandler(PhaseNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(PhaseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}