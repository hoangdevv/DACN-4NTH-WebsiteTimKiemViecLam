package com.codeforworks.NTH_WorkFinder.controller;

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

//    Tạo kỹ năng mới cho ứng viên
    @PostMapping
    public ResponseEntity<CandidateSkillResponseDTO> createCandidateSkill(@RequestBody CandidateSkillRequestDTO candidateSkillRequestDTO) {
        CandidateSkillResponseDTO createdCandidateSkill = candidateSkillService.createCandidateSkill(candidateSkillRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidateSkill);
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

//    Cập nhật kỹ năng của ứng viên
    @PutMapping("/{id}")
    public ResponseEntity<CandidateSkillResponseDTO> updateCandidateSkill(
            @PathVariable Long id,
            @RequestBody CandidateSkillRequestDTO candidateSkillRequestDTO) {
        CandidateSkillResponseDTO updatedCandidateSkill = candidateSkillService.updateCandidateSkill(id, candidateSkillRequestDTO);
        return ResponseEntity.ok(updatedCandidateSkill);
    }

//    Xóa kỹ năng của ứng viên
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidateSkill(@PathVariable Long id) {
        candidateSkillService.deleteCandidateSkill(id);
        return ResponseEntity.noContent().build();
    }
}
