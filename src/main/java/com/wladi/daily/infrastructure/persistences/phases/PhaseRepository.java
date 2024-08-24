package com.wladi.daily.infrastructure.persistences.phases;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PhaseRepository extends JpaRepository<PhaseEntity, Long> {
    
    Optional<PhaseEntity> findByTitle(String title);

}