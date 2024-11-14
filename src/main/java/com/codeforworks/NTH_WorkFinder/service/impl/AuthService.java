package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.login.LoginResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.EmployerRegisterDTO;
import com.codeforworks.NTH_WorkFinder.dto.auth.register.UserRegisterDTO;
import com.codeforworks.NTH_WorkFinder.model.Account;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Industry;
import com.codeforworks.NTH_WorkFinder.model.User;
import com.codeforworks.NTH_WorkFinder.repository.AccountRepository;
import com.codeforworks.NTH_WorkFinder.repository.EmployerRepository;
import com.codeforworks.NTH_WorkFinder.repository.IndustryRepository;
import com.codeforworks.NTH_WorkFinder.repository.UserRepository;
import com.codeforworks.NTH_WorkFinder.security.jwt.JwtTokenProvider;
import com.codeforworks.NTH_WorkFinder.security.user.CustomUserDetailsService;
import com.codeforworks.NTH_WorkFinder.service.IAuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private AccountRepository accountRepository;

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


    @Transactional
    @Override
    public User registerUser(UserRegisterDTO userDTO) {
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
        account.setStatus(true);
        account = accountRepository.save(account);

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


    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequest, Account.AccountType expectedAccountType, HttpServletResponse response) {
        // Tìm tài khoản theo email
        Account account = accountRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email hoặc mật khẩu không hợp lệ"));

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

        // Tạo phản hồi đăng nhập
        return new LoginResponseDTO("Đăng nhập thành công", jwtToken);
    }
}
