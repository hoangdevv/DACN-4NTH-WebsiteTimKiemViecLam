package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    @Autowired
    private ICandidateService candidateService;

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable Long id) {
        CandidateResponseDTO candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates() {
        List<CandidateResponseDTO> candidates = candidateService.getAllCandidates();
        return ResponseEntity.ok(candidates);
    }

    @PostMapping("/create")
    public ResponseEntity<CandidateResponseDTO> createCandidate(@RequestBody CandidateRequestDTO candidateRequestDTO) {
        CandidateResponseDTO createdCandidate = candidateService.createCandidate(candidateRequestDTO);
        return ResponseEntity.ok(createdCandidate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CandidateResponseDTO> updateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateRequestDTO candidateRequestDTO) {
        CandidateResponseDTO updatedCandidate = candidateService.updateCandidate(id, candidateRequestDTO);
        return ResponseEntity.ok(updatedCandidate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.noContent().build();
    }
}
