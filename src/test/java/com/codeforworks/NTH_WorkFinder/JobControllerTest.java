package com.codeforworks.NTH_WorkFinder;
import com.codeforworks.NTH_WorkFinder.controller.JobController;
import com.codeforworks.NTH_WorkFinder.dto.job.JobRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.IJobService;
import com.codeforworks.NTH_WorkFinder.service.impl.JobService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JobControllerTest {

    @InjectMocks
    private JobController jobController;

    @Mock
    private IJobService jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateJob() {
        JobRequestDTO jobRequestDTO = new JobRequestDTO();
        jobRequestDTO.setTitle("Java Developer");
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setId(1L);
        jobResponseDTO.setTitle("Java Developer");

        when(jobService.createJob(jobRequestDTO)).thenReturn(jobResponseDTO);

        ResponseEntity<JobResponseDTO> response = jobController.createJob(jobRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobResponseDTO, response.getBody());
    }

    @Test
    void testGetJobById() {
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setId(1L);
        jobResponseDTO.setTitle("Java Developer");

        when(jobService.getJobById(1L)).thenReturn(jobResponseDTO);

        ResponseEntity<JobResponseDTO> response = jobController.getJobById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobResponseDTO, response.getBody());
    }

    @Test
    void testGetAllJobs() {
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setId(1L);
        jobResponseDTO.setTitle("Java Developer");

        when(jobService.getAllJobs()).thenReturn(Collections.singletonList(jobResponseDTO));

        ResponseEntity<List<JobResponseDTO>> response = jobController.getAllJobs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(jobResponseDTO, response.getBody().get(0));
    }

    @Test
    void testUpdateJob() {
        JobRequestDTO jobRequestDTO = new JobRequestDTO();
        jobRequestDTO.setTitle("Updated Java Developer");
        JobResponseDTO jobResponseDTO = new JobResponseDTO();
        jobResponseDTO.setId(1L);
        jobResponseDTO.setTitle("Updated Java Developer");

        when(jobService.updateJob(1L, jobRequestDTO)).thenReturn(jobResponseDTO);

        ResponseEntity<JobResponseDTO> response = jobController.updateJob(1L, jobRequestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jobResponseDTO, response.getBody());
    }

    @Test
    void testDeleteJob() {
        doNothing().when(jobService).deleteJob(1L);

        ResponseEntity<Void> response = jobController.deleteJob(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(jobService, times(1)).deleteJob(1L);
    }
}
