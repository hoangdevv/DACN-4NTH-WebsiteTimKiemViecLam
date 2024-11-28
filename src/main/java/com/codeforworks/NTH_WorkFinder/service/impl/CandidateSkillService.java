package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateProfileDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.CandidateMapper;
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
    public CandidateProfileDTO addSkillToCandidate(Long candidateId, CandidateSkillRequestDTO candidateSkillRequestDTO) {
        // Lấy ứng viên từ database
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Candidate"));

        // Lấy kỹ năng từ database
        Skill skill = skillRepository.findById(candidateSkillRequestDTO.getSkillId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Skill"));

        // Kiểm tra nếu ứng viên đã có kỹ năng này rồi
        boolean exists = candidateSkillRepository.existsByCandidateAndSkill(candidate, skill);
        if (exists) {
            throw new RuntimeException("Ứng viên đã có kỹ năng này.");
        }

        // Tạo CandidateSkill
        CandidateSkill candidateSkill = new CandidateSkill();
        candidateSkill.setCandidate(candidate);
        candidateSkill.setSkill(skill);
        candidateSkill.setProficiencyLevel(CandidateSkill.ProficiencyLevel.valueOf(candidateSkillRequestDTO.getProficiencyLevel()));

        // Lưu kỹ năng vào database
        candidateSkillRepository.save(candidateSkill);

        // Lấy lại thông tin ứng viên sau khi thêm kỹ năng
        CandidateProfileDTO candidateProfileDTO = CandidateMapper.INSTANCE.toCandidateProfileDTO(candidate);
        return candidateProfileDTO;
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

}
