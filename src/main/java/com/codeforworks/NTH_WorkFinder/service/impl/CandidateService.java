package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateProfileDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.mapper.CandidateMapper;
import com.codeforworks.NTH_WorkFinder.model.Candidate;
import com.codeforworks.NTH_WorkFinder.model.CandidateSkill;
import com.codeforworks.NTH_WorkFinder.model.Skill;
import com.codeforworks.NTH_WorkFinder.model.User;
import com.codeforworks.NTH_WorkFinder.repository.CandidateRepository;
import com.codeforworks.NTH_WorkFinder.repository.CandidateSkillRepository;
import com.codeforworks.NTH_WorkFinder.repository.SkillRepository;
import com.codeforworks.NTH_WorkFinder.repository.UserRepository;
import com.codeforworks.NTH_WorkFinder.service.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateService implements ICandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CandidateProfileDTO getCandidateProfileById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Candidate"));
        return CandidateMapper.INSTANCE.toCandidateProfileDTO(candidate);
    }

    @Override
    public List<CandidateResponseDTO> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidates.stream()
                .map(CandidateMapper.INSTANCE::toCandidateResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateProfileDTO createCandidate(CandidateRequestDTO candidateRequestDTO, Long authenticatedUserId) {

        // Kiểm tra xem userId trong request có trùng với userId từ token không
        if (!candidateRequestDTO.getUserId().equals(authenticatedUserId)) {
            throw new AccessDeniedException("Không được phép tạo ứng viên cho người dùng khác.");
        }

        if (candidateRepository.existsByUserId(candidateRequestDTO.getUserId())) {
            throw new RuntimeException("Ứng viên đã tồn tại cho User này.");
        }

        Candidate candidate = CandidateMapper.INSTANCE.toCandidateEntity(candidateRequestDTO);
        User user = userRepository.findById(candidateRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User"));
        candidate.setUser(user);

//        Candidate savedCandidate = candidateRepository.save(candidate);

        candidate.setCode("APP-" + String.format("%05d", candidateRepository.count() + 1));
        Candidate savedCandidate = candidateRepository.save(candidate);


        return CandidateMapper.INSTANCE.toCandidateProfileDTO(savedCandidate);
    }


    @Override
    public CandidateProfileDTO updateCandidate(Long id, CandidateRequestDTO candidateRequestDTO) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Candidate"));

        candidate.setPhone(candidateRequestDTO.getPhone());
        candidate.setAddress(candidateRequestDTO.getAddress());
        candidate.setLocation(candidateRequestDTO.getLocation());
        candidate.setBirthday(candidateRequestDTO.getBirthday());
        candidate.setSex(candidateRequestDTO.getSex());
        candidate.setAvatar(candidateRequestDTO.getAvatar());
        candidate.setAttachedFile(candidateRequestDTO.getAttachedFile());

        Candidate updatedCandidate = candidateRepository.save(candidate);
        return CandidateMapper.INSTANCE.toCandidateProfileDTO(updatedCandidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        if (!candidateRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy Candidate");
        }
        candidateRepository.deleteById(id);
    }
}
