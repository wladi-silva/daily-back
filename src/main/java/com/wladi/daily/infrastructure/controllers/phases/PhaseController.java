package com.wladi.daily.infrastructure.controllers.phases;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wladi.daily.application.usecases.phases.PhaseInteractor;
import com.wladi.daily.domain.entity.phases.Phase;

@RestController
@RequestMapping("/v1/phases")
public class PhaseController {

    private final PhaseInteractor phaseInteractor;
    private final PhaseDtoMapper phaseDtoMapper;

    public PhaseController(PhaseInteractor phaseInteractor, PhaseDtoMapper phaseDtoMapper) {
        this.phaseInteractor = phaseInteractor;
        this.phaseDtoMapper = phaseDtoMapper;
    }

    @GetMapping("/{title}")
    public ResponseEntity<PhaseResponse> getPhase(@PathVariable String title) {
        Phase foundPhase = phaseInteractor.findPhase(title);
        return ResponseEntity.status(HttpStatus.OK).body(phaseDtoMapper.toResponse(foundPhase));
    }

    @GetMapping
    public ResponseEntity<List<PhaseResponse>> getAllPhases() {
        List<Phase> phases = phaseInteractor.findAllPhases();
        return ResponseEntity.status(HttpStatus.OK).body(phases.stream()
            .map(phaseDtoMapper::toResponse)
            .toList());
    }

    @PostMapping
    public ResponseEntity<PhaseResponse> create(@RequestBody PhaseRequest phaseRequest) {
        Phase phaseDomain = phaseDtoMapper.toPhase(phaseRequest);
        Phase savedPhase = phaseInteractor.createPhase(phaseDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body(phaseDtoMapper.toResponse(savedPhase));
    }

    @PutMapping("/{title}")
    public ResponseEntity<PhaseResponse> update(@PathVariable String title, @RequestBody PhaseRequest phaseRequest) {
        Phase phaseDomain = phaseDtoMapper.toPhase(title, phaseRequest);
        Phase updatedPhase = phaseInteractor.updatePhase(phaseDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body(phaseDtoMapper.toResponse(updatedPhase));
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<Void> delete(@PathVariable String title) {
        phaseInteractor.deletePhase(title);
        return ResponseEntity.noContent().build();
    }

}
