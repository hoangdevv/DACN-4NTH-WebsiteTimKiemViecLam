package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateProfileDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.error.ErrorResponse;
import com.codeforworks.NTH_WorkFinder.security.user.CustomUserDetails;
import com.codeforworks.NTH_WorkFinder.service.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private ICandidateService candidateService;

//    Lấy thông tin chi tiết của ứng viên
    @GetMapping("/{id}")
    public ResponseEntity<CandidateProfileDTO> getCandidateProfile(@PathVariable Long id) {
        CandidateProfileDTO candidateProfile = candidateService.getCandidateProfileById(id);
        return ResponseEntity.ok(candidateProfile);
    }

//    Lấy danh sách tất cả ứng viên
    @GetMapping
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates() {
        List<CandidateResponseDTO> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

//    Tạo mới một ứng viên
    @PostMapping
    public ResponseEntity<Object> createCandidate(@RequestBody CandidateRequestDTO candidateRequestDTO, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long authenticatedUserId = userDetails.getAccount().getUser().getId();

        try {
            CandidateProfileDTO createdCandidate = candidateService.createCandidate(candidateRequestDTO, authenticatedUserId);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCandidate);
        } catch (AccessDeniedException ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        } catch (RuntimeException ex) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }


//    Cập nhật thông tin ứng viên
    @PutMapping("/{id}")
    public ResponseEntity<CandidateProfileDTO> updateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateRequestDTO candidateRequestDTO) {
        CandidateProfileDTO updatedCandidate = candidateService.updateCandidate(id, candidateRequestDTO);
        return ResponseEntity.ok(updatedCandidate);
    }

//    Xóa ứng viên
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
