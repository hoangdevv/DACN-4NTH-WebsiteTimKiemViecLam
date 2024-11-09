package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.application.ApplicationRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.application.ApplicationResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.application.ApplicationStatusUpdateDTO;
import com.codeforworks.NTH_WorkFinder.mapper.ApplicationMapper;
import com.codeforworks.NTH_WorkFinder.model.Application;
import com.codeforworks.NTH_WorkFinder.model.Candidate;
import com.codeforworks.NTH_WorkFinder.model.Job;
import com.codeforworks.NTH_WorkFinder.repository.ApplicationRepository;
import com.codeforworks.NTH_WorkFinder.repository.CandidateRepository;
import com.codeforworks.NTH_WorkFinder.repository.JobRepository;
import com.codeforworks.NTH_WorkFinder.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService implements IApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public ApplicationResponseDTO getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Application"));
        return ApplicationMapper.INSTANCE.toApplicationResponseDTO(application);
    }

    @Override
    public List<ApplicationResponseDTO> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        return applications.stream()
                .map(ApplicationMapper.INSTANCE::toApplicationResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponseDTO createApplication(ApplicationRequestDTO applicationRequestDTO) {
        // Kiểm tra trùng lặp ứng tuyển
        if (applicationRepository.existsByCandidateIdAndJobId(
                applicationRequestDTO.getCandidateId(), applicationRequestDTO.getJobId())) {
            throw new RuntimeException("Ứng viên đã ứng tuyển công việc này rồi.");
        }

        Application application = ApplicationMapper.INSTANCE.toApplicationEntity(applicationRequestDTO);

        // Tìm và thiết lập Candidate
        Candidate candidate = candidateRepository.findById(applicationRequestDTO.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Candidate"));
        application.setCandidate(candidate);

        // Tìm và thiết lập Job
        Job job = jobRepository.findById(applicationRequestDTO.getJobId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Job"));
        application.setJob(job);

        Application savedApplication = applicationRepository.save(application);
        return ApplicationMapper.INSTANCE.toApplicationResponseDTO(savedApplication);
    }

    @Override
    public ApplicationResponseDTO updateApplicationStatus(Long id, ApplicationStatusUpdateDTO statusUpdateDTO) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Application"));

        // Chuyển đổi chuỗi sang ApplicationStatus enum
        Application.ApplicationStatus status = Application.ApplicationStatus.valueOf(statusUpdateDTO.getStatus());
        application.setStatus(status);

        Application updatedApplication = applicationRepository.save(application);
        return ApplicationMapper.INSTANCE.toApplicationResponseDTO(updatedApplication);
    }

    @Override
    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy Application");
        }
        applicationRepository.deleteById(id);
    }
}
