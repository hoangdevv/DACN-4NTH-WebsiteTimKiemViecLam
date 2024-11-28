package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.EmployerMapper;
import com.codeforworks.NTH_WorkFinder.mapper.SubscriptionMapper;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Industry;
import com.codeforworks.NTH_WorkFinder.repository.AccountRepository;
import com.codeforworks.NTH_WorkFinder.repository.EmployerRepository;
import com.codeforworks.NTH_WorkFinder.repository.IndustryRepository;
import com.codeforworks.NTH_WorkFinder.service.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerService implements IEmployerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployerResponseDTO getEmployerById(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));
        return EmployerMapper.INSTANCE.toEmployerResponseDTO(employer);
    }

    @Override
    public List<EmployerResponseDTO> getAllEmployers() {
        List<Employer> employers = employerRepository.findAll();
        return employers.stream()
                .map(EmployerMapper.INSTANCE::toEmployerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployerResponseDTO createEmployer(EmployerRequestDTO employerRequestDTO) {
        Employer employer = EmployerMapper.INSTANCE.toEmployerEntity(employerRequestDTO);

        // Thiết lập Industry cho Employer
        Industry industry = industryRepository.findById(employerRequestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Industry"));
        employer.setIndustry(industry);

        Employer savedEmployer = employerRepository.save(employer);
        return EmployerMapper.INSTANCE.toEmployerResponseDTO(savedEmployer);
    }

    @Override
    public EmployerResponseDTO updateEmployer(Long id, EmployerRequestDTO employerRequestDTO) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));

        // Cập nhật các thông tin của Employer
        employer.setCompanyName(employerRequestDTO.getCompanyName());
        employer.setCompanyPhone(employerRequestDTO.getCompanyPhone());
        employer.setCompanyAddress(employerRequestDTO.getCompanyAddress());
        employer.setCompanyWebsite(employerRequestDTO.getCompanyWebsite());
        employer.setCompanyDescription(employerRequestDTO.getCompanyDescription());
        employer.setLocation(employerRequestDTO.getLocation());

        Industry industry = industryRepository.findById(employerRequestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Industry"));
        employer.setIndustry(industry);

        Employer updatedEmployer = employerRepository.save(employer);
        return EmployerMapper.INSTANCE.toEmployerResponseDTO(updatedEmployer);
    }

    @Override
    public void deleteEmployer(Long id) {
        if (!employerRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy Employer");
        }
        employerRepository.deleteById(id);
    }

    @Override
    public List<SubscriptionResponseDTO> getEmployerSubscriptions(Long employerId) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));

        return employer.getSubscriptions().stream()
                .map(SubscriptionMapper.INSTANCE::toSubscriptionResponseDTO)
                .collect(Collectors.toList());
    }
}
