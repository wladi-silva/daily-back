package com.wladi.daily.application.usecases.phases.exceptions;

public class PhaseNotFoundException extends RuntimeException {
    public PhaseNotFoundException(String message) {
        super(message);
    }
}
