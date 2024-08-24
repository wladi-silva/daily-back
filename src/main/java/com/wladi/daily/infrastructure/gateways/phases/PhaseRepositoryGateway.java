package com.wladi.daily.infrastructure.gateways.phases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wladi.daily.application.usecases.phases.exceptions.PhaseNotFoundException;
import com.wladi.daily.application.usecases.phases.gateways.PhaseGateway;
import com.wladi.daily.domain.entity.phases.Phase;
import com.wladi.daily.infrastructure.persistences.phases.PhaseEntity;
import com.wladi.daily.infrastructure.persistences.phases.PhaseRepository;

@Component
public class PhaseRepositoryGateway implements PhaseGateway {

    private final PhaseRepository phaseRepository;
    private final PhaseEntityMapper phaseEntityMapper;

    public PhaseRepositoryGateway(PhaseRepository phaseRepository, PhaseEntityMapper phaseEntityMapper) {
        this.phaseRepository = phaseRepository;
        this.phaseEntityMapper = phaseEntityMapper;
    }

    @Override
    public Phase createPhase(Phase phase) {
        PhaseEntity phaseEntity = phaseEntityMapper.toEntity(phase);
        PhaseEntity savedPhaseEntity = phaseRepository.save(phaseEntity);
        return phaseEntityMapper.toPhaseDomain(savedPhaseEntity);
    }

    @Override
    public Phase updatePhase(Phase phase) {
        PhaseEntity existingPhaseEntity = phaseRepository.findByTitle(phase.title())
            .orElseThrow(() -> new RuntimeException("User not found with title: " + phase.title()));

        existingPhaseEntity.setTitle(phase.title());
        existingPhaseEntity.setSubtitle(phase.subtitle());
        existingPhaseEntity.setClassName(phase.className());

        PhaseEntity updatedPhaseEntity = phaseRepository.save(existingPhaseEntity);
        return phaseEntityMapper.toPhaseDomain(updatedPhaseEntity);
    }

    @Override
    public void deletePhase(String title) {
        PhaseEntity phaseEntity = phaseRepository.findByTitle(title)
            .orElseThrow(() -> new PhaseNotFoundException("User not found with title: " + title));

        phaseRepository.delete(phaseEntity);
    }

    @Override
    public Phase findPhase(String title) {
        PhaseEntity phaseEntity = phaseRepository.findByTitle(title)
            .orElseThrow(() -> new PhaseNotFoundException("User not found with title: " + title));

        return phaseEntityMapper.toPhaseDomain(phaseEntity);
    }

    @Override
    public List<Phase> findAllPhases() {
        List<PhaseEntity> phaseEntities = phaseRepository.findAll();
        return phaseEntities.stream()
                            .map(phaseEntityMapper::toPhaseDomain)
                            .collect(Collectors.toList());
    }

}