package com.wladi.daily.application.usecases.phases.gateways;

import java.util.List;

import com.wladi.daily.domain.entity.phases.Phase;

public interface PhaseGateway {

    Phase createPhase(Phase phase);
    Phase updatePhase(Phase phase);
    void deletePhase(String title);
    Phase findPhase(String title);
    List<Phase> findAllPhases();

}