package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.CandidateSkillMapper;
import com.codeforworks.NTH_WorkFinder.model.Candidate;
import com.codeforworks.NTH_WorkFinder.model.CandidateSkill;
import com.codeforworks.NTH_WorkFinder.model.Skill;
import com.codeforworks.NTH_WorkFinder.repository.CandidateRepository;
import com.codeforworks.NTH_WorkFinder.repository.CandidateSkillRepository;
import com.codeforworks.NTH_WorkFinder.repository.SkillRepository;
import com.codeforworks.NTH_WorkFinder.service.ICandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateSkillService implements ICandidateSkillService {

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public CandidateSkillResponseDTO createCandidateSkill(CandidateSkillRequestDTO candidateSkillRequestDTO) {
        Candidate candidate = candidateRepository.findById(candidateSkillRequestDTO.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Skill skill = skillRepository.findById(candidateSkillRequestDTO.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        CandidateSkill candidateSkill = CandidateSkillMapper.INSTANCE.toCandidateSkillEntity(candidateSkillRequestDTO);
        candidateSkill.setCandidate(candidate);
        candidateSkill.setSkill(skill);

        CandidateSkill savedCandidateSkill = candidateSkillRepository.save(candidateSkill);
        return CandidateSkillMapper.INSTANCE.toCandidateSkillResponseDTO(savedCandidateSkill);
    }

    @Override
    public CandidateSkillResponseDTO getCandidateSkillById(Long id) {
        CandidateSkill candidateSkill = candidateSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CandidateSkill not found"));
        return CandidateSkillMapper.INSTANCE.toCandidateSkillResponseDTO(candidateSkill);
    }

    @Override
    public List<CandidateSkillResponseDTO> getCandidateSkillsByCandidateId(Long candidateId) {
        List<CandidateSkill> candidateSkills = candidateSkillRepository.findByCandidateId(candidateId);
        return candidateSkills.stream()
                .map(CandidateSkillMapper.INSTANCE::toCandidateSkillResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateSkillResponseDTO updateCandidateSkill(Long id, CandidateSkillRequestDTO candidateSkillRequestDTO) {
        CandidateSkill candidateSkill = candidateSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CandidateSkill not found"));

        if (candidateSkillRequestDTO.getCandidateId() != null) {
            Candidate candidate = candidateRepository.findById(candidateSkillRequestDTO.getCandidateId())
                    .orElseThrow(() -> new RuntimeException("Candidate not found"));
            candidateSkill.setCandidate(candidate);
        }

        if (candidateSkillRequestDTO.getSkillId() != null) {
            Skill skill = skillRepository.findById(candidateSkillRequestDTO.getSkillId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));
            candidateSkill.setSkill(skill);
        }

        candidateSkill.setProficiencyLevel(CandidateSkill.ProficiencyLevel.valueOf(candidateSkillRequestDTO.getProficiencyLevel()));

        CandidateSkill updatedCandidateSkill = candidateSkillRepository.save(candidateSkill);
        return CandidateSkillMapper.INSTANCE.toCandidateSkillResponseDTO(updatedCandidateSkill);
    }

    @Override
    public void deleteCandidateSkill(Long id) {
        if (!candidateSkillRepository.existsById(id)) {
            throw new RuntimeException("CandidateSkill not found");
        }
        candidateSkillRepository.deleteById(id);
    }
}
