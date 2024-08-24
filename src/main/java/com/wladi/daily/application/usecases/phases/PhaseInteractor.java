package com.wladi.daily.application.usecases.phases;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wladi.daily.application.usecases.phases.gateways.PhaseGateway;
import com.wladi.daily.domain.entity.phases.Phase;

@Service
public class PhaseInteractor {

    private PhaseGateway phaseGateway;
    
    public PhaseInteractor(PhaseGateway phaseGateway) {
        this.phaseGateway = phaseGateway;
    }

    public Phase createPhase(Phase phase) {
        return phaseGateway.createPhase(phase);
    }

    public Phase updatePhase(Phase phase) {
        return phaseGateway.updatePhase(phase);
    }

    public void deletePhase(String title) {
        phaseGateway.deletePhase(title);
    }

    public List<Phase> findAllPhases() {
        return phaseGateway.findAllPhases();
    }

    public Phase findPhase(String title) {
        return phaseGateway.findPhase(title);
    }

}