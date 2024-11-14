package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.EmployerRegisterDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.UserRegisterDTO;
import com.codeforworks.NTH_WorkFinder.model.Account;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.User;
import com.codeforworks.NTH_WorkFinder.repository.EmployerRepository;
import com.codeforworks.NTH_WorkFinder.repository.UserRepository;
import com.codeforworks.NTH_WorkFinder.service.IAuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<User> registerUser(@RequestBody UserRegisterDTO userDTO) {
        User user = authService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/register/employer")
    public ResponseEntity<Employer> registerEmployer(@RequestBody EmployerRegisterDTO employerDTO) {
        Employer employer = authService.registerEmployer(employerDTO);
        return ResponseEntity.ok(employer);
    }

    /**
     * Endpoint đăng nhập cho người dùng
     *
     * @param loginRequest Đối tượng chứa email và password
     * @param response HttpServletResponse để lưu JWT vào cookie
     * @return ResponseEntity chứa LoginResponseDTO nếu đăng nhập thành công
     */
    @PostMapping("/login/user")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
        LoginResponseDTO loginResponse = authService.login(loginRequest, Account.AccountType.USER, response);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login/employer")
    public ResponseEntity<LoginResponseDTO> loginEmployer(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
        LoginResponseDTO loginResponse = authService.login(loginRequest, Account.AccountType.EMPLOYER, response);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<LoginResponseDTO> loginAdmin(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
        LoginResponseDTO loginResponse = authService.login(loginRequest, Account.AccountType.ADMIN, response);
        return ResponseEntity.ok(loginResponse);
    }
}
