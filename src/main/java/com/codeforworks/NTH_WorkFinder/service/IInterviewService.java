package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewResponseDTO;

import java.util.List;

public interface IInterviewService {

    InterviewResponseDTO createInterview(InterviewRequestDTO interviewRequestDTO);

    InterviewResponseDTO getInterviewById(Long id);

    List<InterviewResponseDTO> getInterviewsByApplicationId(Long applicationId);

    InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO interviewRequestDTO);

    void deleteInterview(Long id);
}
