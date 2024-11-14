package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;

import java.util.List;

public interface IEmployerService {

    EmployerResponseDTO getEmployerById(Long id);

    List<EmployerResponseDTO> getAllEmployers();

    EmployerResponseDTO createEmployer(EmployerRequestDTO employerRequestDTO);

    EmployerResponseDTO updateEmployer(Long id, EmployerRequestDTO employerRequestDTO);

    void deleteEmployer(Long id);

    List<SubscriptionResponseDTO> getEmployerSubscriptions(Long employerId);
}
