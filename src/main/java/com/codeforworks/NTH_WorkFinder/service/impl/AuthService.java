package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.EmployerRegisterDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.UserRegisterDTO;
import com.codeforworks.NTH_WorkFinder.model.*;
import com.codeforworks.NTH_WorkFinder.repository.*;
import com.codeforworks.NTH_WorkFinder.security.jwt.JwtTokenProvider;
import com.codeforworks.NTH_WorkFinder.security.service.AccountVerificationService;
import com.codeforworks.NTH_WorkFinder.security.service.EmailService;
import com.codeforworks.NTH_WorkFinder.service.IAuthService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AccountVerificationService accountVerificationService;

    @Autowired
    private EmailService emailService;

    @Transactional
    @Override
    public User registerUser(UserRegisterDTO userDTO) throws MessagingException {

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Password và Confirm Password không khớp");
        }
        // Kiểm tra nếu email đã tồn tại
        if (accountRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        // Kiểm tra nếu mật khẩu không đáp ứng yêu cầu
//        if (!userDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
//            throw new IllegalArgumentException("Password không đủ định dạng");
//        }
        Account account = new Account();
        account.setEmail(userDTO.getEmail());
        account.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Mã hóa mật khẩu
        account.setAccountType(Account.AccountType.USER);
        // Gán vai trò mặc định cho USER
        Role userRole = roleRepository.findByRoleName("BASIC_USER")
                .orElseThrow(() -> new RuntimeException("Role BASIC_USER không tồn tại"));
        account.getRoles().add(userRole);
        account.setStatus(false);
        account = accountRepository.save(account);

        // Tạo mã OTP và lưu vào bộ nhớ tạm
        String verificationCode = generateVerificationCode();
        accountVerificationService.storeVerificationCode(userDTO.getEmail(), verificationCode);

        // Gửi mã xác thực qua email
        emailService.sendVerificationCode(userDTO.getEmail(), verificationCode);

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        user.setAccount(account);

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public Employer registerEmployer(EmployerRegisterDTO employerDTO) {
        if (!employerDTO.getPassword().equals(employerDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Password và Confirm Password không khớp");
        }
        // Kiểm tra nếu email đã tồn tại
        if (accountRepository.existsByEmail(employerDTO.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }
//        // Kiểm tra nếu mật khẩu không đáp ứng yêu cầu
//        if (!employerDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
//            throw new IllegalArgumentException("Password không đủ định dạng");
//        }
        Account account = new Account();
        account.setEmail(employerDTO.getEmail());
        account.setPassword(passwordEncoder.encode(employerDTO.getPassword())); // Mã hóa mật khẩu
        account.setAccountType(Account.AccountType.EMPLOYER);
        // Gán vai trò mặc định cho EMPLOYER
        Role employerRole = roleRepository.findByRoleName("HR_MANAGER")
                .orElseThrow(() -> new RuntimeException("Role HR_MANAGER không tồn tại"));
        account.getRoles().add(employerRole);
        account.setStatus(true);
        account = accountRepository.save(account);

        Industry industry = industryRepository.findByName(employerDTO.getIndustry())
                .orElseThrow(() -> new IllegalArgumentException("Ngành công nghiệp không tồn tại"));

        Employer employer = new Employer();
        employer.setCompanyName(employerDTO.getCompanyName());
        employer.setCompanyPhone(employerDTO.getCompanyPhone());
        employer.setCompanyAddress(employerDTO.getCompanyAddress());
        employer.setLocation(employerDTO.getLocation());
        employer.setIndustry(industry);
        employer.setAccount(account);

        // Tạo mã `code` duy nhất cho employer trước khi lưu
        employer.setCode("EMP-" + UUID.randomUUID().toString().substring(0, 8));

        return employerRepository.save(employer);
    }

//    xác thực mã
    @Transactional
    @Override
    public void verifyAccount(String email, String code) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Tài khoản không tồn tại"));

        // Kiểm tra mã OTP và thời gian hết hạn
        if (!accountVerificationService.verifyCode(email, code)) {
            throw new RuntimeException("Xác thực thất bại");
        }

        // Xác thực tài khoản thành công
        account.setStatus(true);
        accountRepository.save(account);
    }

//    gửi lại mã
    @Transactional
    @Override
    public void resendVerificationCode(String email) throws MessagingException {
        accountVerificationService.resendVerificationCode(email);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest, Account.AccountType expectedAccountType, HttpServletResponse response)throws MessagingException {
        // Tìm tài khoản theo email
        Account account = accountRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email hoặc mật khẩu không hợp lệ"));

        //Kiểm tra Email đã Kích hoạt trong login
        if (!account.getStatus()) {
            throw new RuntimeException("Tài khoản chưa được xác thực. Vui lòng xác thực tài khoản trước khi đăng nhập.");
        }

        // Kiểm tra loại tài khoản
        if (!account.getAccountType().equals(expectedAccountType)) {
            throw new RuntimeException("Unauthorized access to this endpoint");
        }

        // Xác thực thông tin đăng nhập
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        // Đặt Authentication vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Tạo JWT token
        String jwtToken = jwtTokenProvider.generateToken(authentication);

        // Đặt JWT token vào cookie HTTP-only
        Cookie jwtCookie = new Cookie("jwtToken", jwtToken);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(false); // Đặt true nếu dùng HTTPS
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(24 * 60 * 60); // Thời hạn cookie là 1 ngày
        response.addCookie(jwtCookie);

        // Kiểm tra nếu đây là lần đăng nhập đầu tiên
        if (!account.getLoggedIn()) {
            // Gửi email thông báo đăng ký thành công
            emailService.sendRegistrationSuccessEmail(loginRequest.getEmail(), account.getEmail());

            // Cập nhật trạng thái đăng nhập để chỉ gửi email này một lần
            account.setLoggedIn(true);
            accountRepository.save(account);
        }

        // Tạo phản hồi đăng nhập
        return new LoginResponseDTO("Đăng nhập thành công. Vui lòng kiểm tra email để nhận mã xác thực.", jwtToken);
    }

    // Tạo mã xác thực ngẫu nhiên (6 chữ số)
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);  // Số ngẫu nhiên từ 100000 đến 999999
        return String.valueOf(code);
    }
}
