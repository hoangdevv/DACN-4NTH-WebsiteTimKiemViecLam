package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.IJobSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-skills")
public class JobSkillController {

    @Autowired
    private IJobSkillService jobSkillService;


    @PostMapping
    public ResponseEntity<JobSkillResponseDTO> createJobSkill(@RequestBody JobSkillRequestDTO jobSkillRequestDTO) {
        JobSkillResponseDTO createdJobSkill = jobSkillService.createJobSkill(jobSkillRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJobSkill);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobSkillResponseDTO> getJobSkillById(@PathVariable Long id) {
        JobSkillResponseDTO jobSkill = jobSkillService.getJobSkillById(id);
        return ResponseEntity.ok(jobSkill);
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobSkillResponseDTO>> getJobSkillsByJobId(@PathVariable Long jobId) {
        List<JobSkillResponseDTO> jobSkills = jobSkillService.getJobSkillsByJobId(jobId);
        return ResponseEntity.ok(jobSkills);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobSkillResponseDTO> updateJobSkill(
            @PathVariable Long id,
            @RequestBody JobSkillRequestDTO jobSkillRequestDTO) {
        JobSkillResponseDTO updatedJobSkill = jobSkillService.updateJobSkill(id, jobSkillRequestDTO);
        return ResponseEntity.ok(updatedJobSkill);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobSkill(@PathVariable Long id) {
        jobSkillService.deleteJobSkill(id);
        return ResponseEntity.noContent().build();
    }
}
