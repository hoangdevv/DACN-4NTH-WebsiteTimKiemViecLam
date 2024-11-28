package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.EmployerRegisterDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.UserRegisterDTO;
import com.codeforworks.NTH_WorkFinder.model.Account;
import com.codeforworks.NTH_WorkFinder.model.Admin;
import com.codeforworks.NTH_WorkFinder.repository.AccountRepository;
import com.codeforworks.NTH_WorkFinder.service.IAuthService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userDTO) throws MessagingException {
        authService.registerUser(userDTO);
        return ResponseEntity.ok("Đăng ký thành công. Vui lòng kiểm tra email để nhận mã xác thực.");
    }

    @PostMapping("/register/employer")
    public ResponseEntity<String> registerEmployer(@RequestBody EmployerRegisterDTO employerDTO) throws MessagingException {
        authService.registerEmployer(employerDTO);
        return ResponseEntity.ok("Đăng ký tai khoan NTD thành công.");
    }


    @PostMapping("/create/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Admin> createAdmin( @RequestParam String email, @RequestParam String password) {
        Admin admin = authService.createAdmin(email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(admin);
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

    // Đăng nhập
    @PostMapping("/login/{accountType}")
    public ResponseEntity<LoginResponseDTO> login( @PathVariable("accountType") String accountType,
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

    // Yêu cầu thay đổi mật khẩu
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) throws MessagingException {
        boolean isEmailSent = authService.sendPasswordResetEmail(email);
        if (isEmailSent) {
            return ResponseEntity.ok("Mã xác minh đã được gửi đến email của bạn.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email không tồn tại.");
        }
    }

    // Đặt lại mật khẩu
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email,
                                                @RequestParam String code,
                                                @RequestParam String newPassword) {
        boolean isReset = authService.resetPassword(email, code, newPassword);
        if (isReset) {
            return ResponseEntity.ok("Mật khẩu đã được thay đổi thành công.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã xác minh không hợp lệ hoặc đã hết hạn.");
        }
    }

    //lấy thông tin người dùng đang đăng nhập oauth2
    @GetMapping("/oauth2/me")
    public ResponseEntity<?> getCurrentOAuthUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không có người dùng nào đang đăng nhập.");
        }

        String email = oAuth2User.getAttribute("email");
        Optional<Account> account = accountRepository.findByEmail(email);

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tài khoản không tồn tại.");
        }

        Account userAccount = account.get();
        return ResponseEntity.ok(userAccount);
    }


}
