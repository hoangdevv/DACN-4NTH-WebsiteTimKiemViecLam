package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.InterviewMapper;
import com.codeforworks.NTH_WorkFinder.model.Application;
import com.codeforworks.NTH_WorkFinder.model.Interview;
import com.codeforworks.NTH_WorkFinder.repository.ApplicationRepository;
import com.codeforworks.NTH_WorkFinder.repository.InterviewRepository;
import com.codeforworks.NTH_WorkFinder.service.IInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewService implements IInterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public InterviewResponseDTO createInterview(InterviewRequestDTO interviewRequestDTO) {
        Application application = applicationRepository.findById(interviewRequestDTO.getApplicationId())
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Interview interview = InterviewMapper.INSTANCE.toInterviewEntity(interviewRequestDTO);
        interview.setApplication(application);

        Interview savedInterview = interviewRepository.save(interview);
        return InterviewMapper.INSTANCE.toInterviewResponseDTO(savedInterview);
    }

    @Override
    public InterviewResponseDTO getInterviewById(Long id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        return InterviewMapper.INSTANCE.toInterviewResponseDTO(interview);
    }

    @Override
    public List<InterviewResponseDTO> getInterviewsByApplicationId(Long applicationId) {
        List<Interview> interviews = interviewRepository.findByApplicationId(applicationId);
        return interviews.stream()
                .map(InterviewMapper.INSTANCE::toInterviewResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        interview.setInterviewDate(interviewRequestDTO.getInterviewDate());
        interview.setLocation(interviewRequestDTO.getLocation());
        interview.setStatus(Interview.InterviewStatus.valueOf(interviewRequestDTO.getStatus()));
        interview.setFeedback(interviewRequestDTO.getFeedback());

        Interview updatedInterview = interviewRepository.save(interview);
        return InterviewMapper.INSTANCE.toInterviewResponseDTO(updatedInterview);
    }

    @Override
    public void deleteInterview(Long id) {
        if (!interviewRepository.existsById(id)) {
            throw new RuntimeException("Interview not found");
        }
        interviewRepository.deleteById(id);
    }
}
