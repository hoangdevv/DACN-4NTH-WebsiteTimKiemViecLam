package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerSignupRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.request.LoginRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.response.LoginResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;

import java.util.List;

public interface IEmployerService {
    void registerEmployer(EmployerSignupRequestDTO dto);
    LoginResponseDTO loginEmployer(LoginRequestDTO dto);
    EmployerResponseDTO getEmployerById(Long id);
    List<EmployerResponseDTO> getAllEmployers();
    EmployerResponseDTO createEmployer(EmployerRequestDTO employerRequestDTO);
    EmployerResponseDTO updateEmployer(Long id, EmployerRequestDTO employerRequestDTO);
    void deleteEmployer(Long id);
    List<SubscriptionResponseDTO> getEmployerSubscriptions(Long employerId);
}
