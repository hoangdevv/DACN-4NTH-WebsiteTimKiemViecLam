package com.codeforworks.NTH_WorkFinder;

import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.IEmployerService;
import com.codeforworks.NTH_WorkFinder.service.impl.EmployerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEmployerService employerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEmployer() throws Exception {
        EmployerRequestDTO employerRequestDTO = new EmployerRequestDTO();
        employerRequestDTO.setCompanyName("Tech Corp");

        EmployerResponseDTO employerResponseDTO = new EmployerResponseDTO();
        employerResponseDTO.setId(1L);
        employerResponseDTO.setCompanyName("Tech Corp");

        when(employerService.createEmployer(any(EmployerRequestDTO.class))).thenReturn(employerResponseDTO);

        mockMvc.perform(post("/api/employers/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employerRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.companyName").value("Tech Corp"));
    }

    @Test
    void testGetEmployerById() throws Exception {
        EmployerResponseDTO employerResponseDTO = new EmployerResponseDTO();
        employerResponseDTO.setId(1L);
        employerResponseDTO.setCompanyName("Tech Corp");

        when(employerService.getEmployerById(1L)).thenReturn(employerResponseDTO);

        mockMvc.perform(get("/api/employers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.companyName").value("Tech Corp"));
    }

    @Test
    void testUpdateEmployer() throws Exception {
        EmployerRequestDTO employerRequestDTO = new EmployerRequestDTO();
        employerRequestDTO.setCompanyName("Updated Tech Corp");

        EmployerResponseDTO employerResponseDTO = new EmployerResponseDTO();
        employerResponseDTO.setId(1L);
        employerResponseDTO.setCompanyName("Updated Tech Corp");

        when(employerService.updateEmployer(eq(1L), any(EmployerRequestDTO.class))).thenReturn(employerResponseDTO);

        mockMvc.perform(put("/api/employers/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employerRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Updated Tech Corp"));
    }
}
