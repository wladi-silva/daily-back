package com.wladi.daily.infrastructure.controllers.phases;

import org.springframework.stereotype.Component;

import com.wladi.daily.domain.entity.phases.Phase;

@Component
public class PhaseDtoMapper {
    
    public Phase toPhase(PhaseRequest phaseRequest) {
        return new Phase(
            phaseRequest.title(),
            phaseRequest.subtitle(),
            phaseRequest.className()
        );
    }

    public Phase toPhase(String title, PhaseRequest phaseRequest) {
        return new Phase(
            title,
            phaseRequest.subtitle(),
            phaseRequest.className()
        );
    }

    public PhaseResponse toResponse(Phase phase) {
        return new PhaseResponse(
            phase.title(),
            phase.subtitle(),
            phase.className()
        );
    }

}