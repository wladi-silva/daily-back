package com.wladi.daily.infrastructure.gateways.phases;

import org.springframework.stereotype.Component;

import com.wladi.daily.domain.entity.phases.Phase;
import com.wladi.daily.infrastructure.persistences.phases.PhaseEntity;

@Component
public class PhaseEntityMapper {

    public PhaseEntity toEntity(Phase phase) {
        return PhaseEntity.builder()
            .title(phase.title())
            .subtitle(phase.subtitle())
            .className(phase.className())
            .build();
    }

    public Phase toPhaseDomain(PhaseEntity phaseEntity) {
        return new Phase(
            phaseEntity.getTitle(),
            phaseEntity.getSubtitle(),
            phaseEntity.getClassName()
        );
    }

}