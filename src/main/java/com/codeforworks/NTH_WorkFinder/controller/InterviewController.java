package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.IInterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private IInterviewService interviewService;

//    Tạo mới một cuộc phỏng vấn
    @PostMapping
    public ResponseEntity<InterviewResponseDTO> createInterview(@RequestBody InterviewRequestDTO interviewRequestDTO) {
        InterviewResponseDTO createdInterview = interviewService.createInterview(interviewRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInterview);
    }

//     Lấy thông tin chi tiết của cuộc phỏng vấn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponseDTO> getInterviewById(@PathVariable Long id) {
        InterviewResponseDTO interview = interviewService.getInterviewById(id);
        return ResponseEntity.ok(interview);
    }

//    Lấy danh sách tất cả các cuộc phỏng vấn của một ứng tuyển
    @GetMapping("/application/{applicationId}")
    public ResponseEntity<List<InterviewResponseDTO>> getInterviewsByApplicationId(@PathVariable Long applicationId) {
        List<InterviewResponseDTO> interviews = interviewService.getInterviewsByApplicationId(applicationId);
        return ResponseEntity.ok(interviews);
    }

//  Cập nhật thông tin của cuộc phỏng vấn
    @PutMapping("/{id}")
    public ResponseEntity<InterviewResponseDTO> updateInterview(
            @PathVariable Long id,
            @RequestBody InterviewRequestDTO interviewRequestDTO) {
        InterviewResponseDTO updatedInterview = interviewService.updateInterview(id, interviewRequestDTO);
        return ResponseEntity.ok(updatedInterview);
    }

//    Xóa một cuộc phỏng vấn
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterview(id);
        return ResponseEntity.noContent().build();
    }
}
