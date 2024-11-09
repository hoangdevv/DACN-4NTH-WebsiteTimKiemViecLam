package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillResponseDTO;

import java.util.List;

public interface ICandidateSkillService {

    CandidateSkillResponseDTO createCandidateSkill(CandidateSkillRequestDTO candidateSkillRequestDTO);

    CandidateSkillResponseDTO getCandidateSkillById(Long id);

    List<CandidateSkillResponseDTO> getCandidateSkillsByCandidateId(Long candidateId);

    CandidateSkillResponseDTO updateCandidateSkill(Long id, CandidateSkillRequestDTO candidateSkillRequestDTO);

    void deleteCandidateSkill(Long id);
}
