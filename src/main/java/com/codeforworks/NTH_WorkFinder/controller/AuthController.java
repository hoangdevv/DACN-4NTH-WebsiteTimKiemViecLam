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
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userDTO) throws MessagingException {
        authService.registerUser(userDTO);
        return ResponseEntity.ok("Đăng ký thành công. Vui lòng kiểm tra email để nhận mã xác thực.");
    }

    @PostMapping("/register/employer")
    public ResponseEntity<String> registerEmployer(@RequestBody EmployerRegisterDTO employerDTO) throws MessagingException {
        authService.registerEmployer(employerDTO);
        return ResponseEntity.ok("Đăng ký thành công. Vui lòng kiểm tra email để nhận mã xác thực.");
    }

    // Xác thực tài khoản bằng mã OTP
    @PostMapping("/verify")
    public ResponseEntity<String> verifyAccount(@RequestParam String email, @RequestParam String code) {
        authService.verifyAccount(email, code);
        return ResponseEntity.ok("Tài khoản đã được xác thực thành công.");
    }

    // Gửi lại mã OTP cho tài khoản
    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestParam String email) throws MessagingException {
        authService.resendVerificationCode(email);
        return ResponseEntity.ok("Mã xác thực mới đã được gửi.");
    }

    /**
     * Endpoint đăng nhập cho người dùng
     *
     * @param loginRequest Đối tượng chứa email và password
     * @param response HttpServletResponse để lưu JWT vào cookie
     * @return ResponseEntity chứa LoginResponseDTO nếu đăng nhập thành công
     */
//    @PostMapping("/login/user")
//    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) throws MessagingException {
//        LoginResponseDTO loginResponse = authService.login(loginRequest, Account.AccountType.USER, response);
//        return ResponseEntity.ok(loginResponse);
//    }
//
//    @PostMapping("/login/employer")
//    public ResponseEntity<LoginResponseDTO> loginEmployer(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) throws MessagingException {
//        LoginResponseDTO loginResponse = authService.login(loginRequest, Account.AccountType.EMPLOYER, response);
//        return ResponseEntity.ok(loginResponse);
//    }
//
//    @PostMapping("/login/admin")
//    public ResponseEntity<LoginResponseDTO> loginAdmin(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) throws MessagingException {
//        LoginResponseDTO loginResponse = authService.login(loginRequest, Account.AccountType.ADMIN, response);
//        return ResponseEntity.ok(loginResponse);
//    }
    // Đăng nhập
    @PostMapping("/login/{accountType}")
    public ResponseEntity<LoginResponseDTO> login(
            @PathVariable("accountType") String accountType,
            @RequestBody LoginRequestDTO loginRequest,
            HttpServletResponse response) throws MessagingException {

        // Xác định loại tài khoản từ chuỗi
        Account.AccountType expectedAccountType;
        try {
            expectedAccountType = Account.AccountType.valueOf(accountType.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new LoginResponseDTO("Loại tài khoản không hợp lệ", null));
        }

        // Thực hiện đăng nhập
        LoginResponseDTO responseDTO = authService.login(loginRequest, expectedAccountType, response);
        return ResponseEntity.ok(responseDTO);
    }
}
