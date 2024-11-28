package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateProfileDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;

import java.util.List;

public interface ICandidateService {

    CandidateProfileDTO getCandidateProfileById(Long id);

    List<CandidateResponseDTO> getAllCandidates();

    CandidateProfileDTO createCandidate(CandidateRequestDTO candidateRequestDTO, Long authenticatedUserId);

    CandidateProfileDTO updateCandidate(Long id, CandidateRequestDTO candidateRequestDTO);

    void deleteCandidate(Long id);
}
