package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.application.ApplicationRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.application.ApplicationResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.application.ApplicationStatusUpdateDTO;

import java.util.List;

public interface IApplicationService {
    ApplicationResponseDTO getApplicationById(Long id);
    List<ApplicationResponseDTO> getAllApplications();
    ApplicationResponseDTO createApplication(ApplicationRequestDTO applicationRequestDTO);
    ApplicationResponseDTO updateApplicationStatus(Long id, ApplicationStatusUpdateDTO statusUpdateDTO);
    void deleteApplication(Long id);
}
