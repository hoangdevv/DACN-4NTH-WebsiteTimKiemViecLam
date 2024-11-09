package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerSignupRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.request.LoginRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.response.LoginResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    @Autowired
    private IEmployerService employerService;

    // API đăng ký cho NTD
    @PostMapping("/signup")
    public ResponseEntity<String> registerEmployer(@RequestBody EmployerSignupRequestDTO dto) {
        employerService.registerEmployer(dto);
        return ResponseEntity.ok("Employer registered successfully!");
    }

    // API đăng nhập cho NTD
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginEmployer(@RequestBody LoginRequestDTO dto) {
        LoginResponseDTO response = employerService.loginEmployer(dto);
        return ResponseEntity.ok(response);
    }

//    Lấy thông tin NTD theo ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployerResponseDTO> getEmployerById(@PathVariable Long id) {
        EmployerResponseDTO employer = employerService.getEmployerById(id);
        return ResponseEntity.ok(employer);
    }

//     Lấy danh sách tất cả NTD
    @GetMapping
    public ResponseEntity<List<EmployerResponseDTO>> getAllEmployers() {
        List<EmployerResponseDTO> employers = employerService.getAllEmployers();
        return ResponseEntity.ok(employers);
    }

    //    Tạo mới một NTD
    @PostMapping
    public ResponseEntity<EmployerResponseDTO> createEmployer(@RequestBody EmployerRequestDTO employerRequestDTO) {
        EmployerResponseDTO createdEmployer = employerService.createEmployer(employerRequestDTO);
        return ResponseEntity.ok(createdEmployer);
    }

    //    Cập nhật thông tin NTD
    @PutMapping("/{id}")
    public ResponseEntity<EmployerResponseDTO> updateEmployer(
            @PathVariable Long id,
            @RequestBody EmployerRequestDTO employerRequestDTO) {
        EmployerResponseDTO updatedEmployer = employerService.updateEmployer(id, employerRequestDTO);
        return ResponseEntity.ok(updatedEmployer);
    }

    //     Xóa NTD khỏi hệ thống
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.noContent().build();
    }

//    Lấy danh sách gói dịch vụ của NTD
    @GetMapping("/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionResponseDTO>> getEmployerSubscriptions(@PathVariable Long id) {
        List<SubscriptionResponseDTO> subscriptions = employerService.getEmployerSubscriptions(id);
        return ResponseEntity.ok(subscriptions);
    }
}
