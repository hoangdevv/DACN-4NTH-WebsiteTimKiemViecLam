package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateProfileDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillResponseDTO;

import java.util.List;

public interface ICandidateSkillService {

    CandidateProfileDTO addSkillToCandidate(Long candidateId, CandidateSkillRequestDTO candidateSkillRequestDTO);

    CandidateSkillResponseDTO getCandidateSkillById(Long id);

    List<CandidateSkillResponseDTO> getCandidateSkillsByCandidateId(Long candidateId);

}
