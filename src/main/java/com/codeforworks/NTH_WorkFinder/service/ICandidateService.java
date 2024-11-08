package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateResponseDTO;

import java.util.List;

public interface ICandidateService {
    CandidateResponseDTO getCandidateById(Long id);
    List<CandidateResponseDTO> getAllCandidates();
    CandidateResponseDTO createCandidate(CandidateRequestDTO candidateRequestDTO);
    CandidateResponseDTO updateCandidate(Long id, CandidateRequestDTO candidateRequestDTO);
    void deleteCandidate(Long id);
}
