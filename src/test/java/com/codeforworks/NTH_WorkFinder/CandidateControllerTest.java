package com.codeforworks.NTH_WorkFinder;

import com.codeforworks.NTH_WorkFinder.controller.CandidateController;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.ICandidateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ICandidateService candidateService;

    @InjectMocks
    private CandidateController  candidateController;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCandidate() throws Exception {
        CandidateRequestDTO candidateRequestDTO = new CandidateRequestDTO();
        candidateRequestDTO.setPhone("123456789");
        candidateRequestDTO.setAddress("123 Main St");

        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();
        candidateResponseDTO.setId(1L);
        candidateResponseDTO.setPhone("123456789");
        candidateResponseDTO.setAddress("123 Main St");

        when(candidateService.createCandidate(any(CandidateRequestDTO.class))).thenReturn(candidateResponseDTO);

        mockMvc.perform(post("/api/candidates/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidateRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.phone").value("123456789"))
                .andExpect(jsonPath("$.address").value("123 Main St"));
    }

    @Test
    void testGetCandidateById() throws Exception {
        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();
        candidateResponseDTO.setId(1L);
        candidateResponseDTO.setPhone("123456789");
        candidateResponseDTO.setAddress("123 Main St");

        when(candidateService.getCandidateById(1L)).thenReturn(candidateResponseDTO);

        mockMvc.perform(get("/api/candidates/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.phone").value("123456789"))
                .andExpect(jsonPath("$.address").value("123 Main St"));
    }

    @Test
    void testGetAllCandidates() throws Exception {
        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();
        candidateResponseDTO.setId(1L);
        candidateResponseDTO.setPhone("123456789");
        candidateResponseDTO.setAddress("123 Main St");

        List<CandidateResponseDTO> candidates = Collections.singletonList(candidateResponseDTO);
        when(candidateService.getAllCandidates()).thenReturn(candidates);

        mockMvc.perform(get("/api/candidates/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].phone").value("123456789"))
                .andExpect(jsonPath("$[0].address").value("123 Main St"));
    }

    @Test
    void testUpdateCandidate() throws Exception {
        CandidateRequestDTO candidateRequestDTO = new CandidateRequestDTO();
        candidateRequestDTO.setPhone("987654321");
        candidateRequestDTO.setAddress("456 Elm St");

        CandidateResponseDTO candidateResponseDTO = new CandidateResponseDTO();
        candidateResponseDTO.setId(1L);
        candidateResponseDTO.setPhone("987654321");
        candidateResponseDTO.setAddress("456 Elm St");

        when(candidateService.updateCandidate(eq(1L), any(CandidateRequestDTO.class))).thenReturn(candidateResponseDTO);

        mockMvc.perform(put("/api/candidates/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidateRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.phone").value("987654321"))
                .andExpect(jsonPath("$.address").value("456 Elm St"));
    }

    @Test
    void testDeleteCandidate() throws Exception {
        mockMvc.perform(delete("/api/candidates/delete/1"))
                .andExpect(status().isNoContent());
    }
}