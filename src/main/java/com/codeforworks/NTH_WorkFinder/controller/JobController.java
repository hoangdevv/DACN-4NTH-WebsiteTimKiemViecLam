package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.job.JobRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobSearchCriteria;
import com.codeforworks.NTH_WorkFinder.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private IJobService jobService;

    // Tạo một công việc mới
    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobRequestDTO jobRequestDTO) {
        JobResponseDTO createdJob = jobService.createJob(jobRequestDTO);
        return ResponseEntity.ok(createdJob);
    }

    // Lấy thông tin một công việc theo ID
    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Long id) {
        JobResponseDTO job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }

    // Lấy danh sách tất cả công việc
    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {
        List<JobResponseDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    // Cập nhật thông tin công việc theo ID
    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(
            @PathVariable Long id,
            @RequestBody JobRequestDTO jobRequestDTO) {
        JobResponseDTO updatedJob = jobService.updateJob(id, jobRequestDTO);
        return ResponseEntity.ok(updatedJob);
    }

    // Xóa công việc theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    //tìm kiếm công việc.
    @PostMapping("/search")
    public ResponseEntity<List<JobResponseDTO>> searchJobs(@RequestBody JobSearchCriteria criteria) {
        List<JobResponseDTO> jobs = jobService.searchJobs(criteria);
        return ResponseEntity.ok(jobs);
    }
}
