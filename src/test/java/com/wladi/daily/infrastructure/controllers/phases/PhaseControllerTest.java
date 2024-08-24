package com.wladi.daily.infrastructure.controllers.phases;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wladi.daily.application.usecases.phases.PhaseInteractor;
import com.wladi.daily.domain.entity.phases.Phase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PhaseController.class)
public class PhaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhaseInteractor phaseInteractor;

    @MockBean
    private PhaseDtoMapper phaseDtoMapper;

    private Phase phase;
    private PhaseResponse phaseResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        phase = new Phase("genesis", "subtitle", "className");
        phaseResponse = new PhaseResponse("genesis", "subtitle", "className");
    }

    @Test
    void testGetPhase() throws Exception {
        when(phaseInteractor.findPhase("genesis")).thenReturn(phase);
        when(phaseDtoMapper.toResponse(phase)).thenReturn(phaseResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/phases/genesis"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("genesis"))
                .andExpect(jsonPath("$.subtitle").value("subtitle"))
                .andExpect(jsonPath("$.className").value("className"))
                .andDo(MockMvcResultHandlers.print());

        verify(phaseInteractor, times(1)).findPhase("genesis");
    }

    @Test
    void testGetAllPhases() throws Exception {
        List<Phase> phases = List.of(phase);

        when(phaseInteractor.findAllPhases()).thenReturn(phases);
        when(phaseDtoMapper.toResponse(phase)).thenReturn(phaseResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/phases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("genesis"))
                .andExpect(jsonPath("$[0].subtitle").value("subtitle"))
                .andExpect(jsonPath("$[0].className").value("className"))
                .andDo(MockMvcResultHandlers.print());

        verify(phaseInteractor, times(1)).findAllPhases();
    }

    @Test
    void testCreatePhase() throws Exception {
        PhaseRequest phaseRequest = new PhaseRequest("genesis", "subtitle", "className");
        when(phaseDtoMapper.toPhase(phaseRequest)).thenReturn(phase);
        when(phaseInteractor.createPhase(phase)).thenReturn(phase);
        when(phaseDtoMapper.toResponse(phase)).thenReturn(phaseResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/phases")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(phaseRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("genesis"))
                .andExpect(jsonPath("$.subtitle").value("subtitle"))
                .andExpect(jsonPath("$.className").value("className"))
                .andDo(MockMvcResultHandlers.print());

        verify(phaseInteractor, times(1)).createPhase(phase);
    }

    @Test
    void testUpdatePhase() throws Exception {
        PhaseRequest phaseRequest = new PhaseRequest("genesis", "subtitle", "className");
        when(phaseDtoMapper.toPhase("genesis", phaseRequest)).thenReturn(phase);
        when(phaseInteractor.updatePhase(phase)).thenReturn(phase);
        when(phaseDtoMapper.toResponse(phase)).thenReturn(phaseResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/phases/genesis")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(phaseRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("genesis"))
                .andExpect(jsonPath("$.subtitle").value("subtitle"))
                .andExpect(jsonPath("$.className").value("className"))
                .andDo(MockMvcResultHandlers.print());

        verify(phaseInteractor, times(1)).updatePhase(phase);
    }

    @Test
    void testDeletePhase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/phases/genesis"))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print());

        verify(phaseInteractor, times(1)).deletePhase("genesis");
    }
    
}
