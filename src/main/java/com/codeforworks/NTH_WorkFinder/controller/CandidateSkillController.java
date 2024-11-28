package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateProfileDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.ICandidateSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate-skills")
public class CandidateSkillController {

    @Autowired
    private ICandidateSkillService candidateSkillService;

    //    Them ki nang vao ứng viên
    @PostMapping("/{candidateId}")
    public ResponseEntity<CandidateProfileDTO> addSkillToCandidate
    (@PathVariable Long candidateId,
     @RequestBody CandidateSkillRequestDTO candidateSkillRequestDTO) {

        CandidateProfileDTO updatedProfile = candidateSkillService.addSkillToCandidate(candidateId, candidateSkillRequestDTO);
        return ResponseEntity.ok(updatedProfile);
    }


//    Lấy thông tin kỹ năng của ứng viên theo id
    @GetMapping("/{id}")
    public ResponseEntity<CandidateSkillResponseDTO> getCandidateSkillById(@PathVariable Long id) {
        CandidateSkillResponseDTO candidateSkill = candidateSkillService.getCandidateSkillById(id);
        return ResponseEntity.ok(candidateSkill);
    }

//    Lấy danh sách tất cả kỹ năng của ứng viên
    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<CandidateSkillResponseDTO>> getCandidateSkillsByCandidateId(@PathVariable Long candidateId) {
        List<CandidateSkillResponseDTO> candidateSkills = candidateSkillService.getCandidateSkillsByCandidateId(candidateId);
        return ResponseEntity.ok(candidateSkills);
    }


}
