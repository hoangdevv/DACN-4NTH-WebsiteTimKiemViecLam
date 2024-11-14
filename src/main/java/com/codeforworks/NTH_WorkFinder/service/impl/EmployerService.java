package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.EmployerMapper;
import com.codeforworks.NTH_WorkFinder.mapper.SubscriptionMapper;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Industry;
import com.codeforworks.NTH_WorkFinder.repository.AccountRepository;
import com.codeforworks.NTH_WorkFinder.repository.EmployerRepository;
import com.codeforworks.NTH_WorkFinder.repository.IndustryRepository;
import com.codeforworks.NTH_WorkFinder.service.IEmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerService implements IEmployerService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Override
//    public void registerEmployer(EmployerSignupRequestDTO dto) {
//        // Kiểm tra email đã tồn tại chưa và pw
//        if (accountRepository.findByEmail(dto.getEmail()).isPresent()) {
//            throw new RuntimeException("Email đã được sử dụng");
//        }
//        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
//            throw new RuntimeException("Mật khẩu và xác nhận mật khẩu không khớp");
//        }
//
//
//        // Tạo và thiết lập Account
//        Account account = new Account();
//        account.setEmail(dto.getEmail());
//        account.setPassword(passwordEncoder.encode(dto.getPassword())); // Mã hóa mật khẩu
//        account.setAccountType(Account.AccountType.EMPLOYER); // Đặt loại tài khoản là EMPLOYER
//        account.setStatus(true);
//
//        Account savedAccount = accountRepository.save(account);
//
//        // Tạo và thiết lập Employer
//        Employer employer = new Employer();
//        employer.setAccount(savedAccount); // Liên kết Account với Employer
//        employer.setCompanyName(dto.getCompanyName());
//        employer.setCompanyPhone(dto.getCompanyPhone());
//        employer.setCompanyAddress(dto.getCompanyAddress());
//        employer.setLocation(dto.getLocation());
//
//        // Tìm Industry dựa trên industryId và gán cho Employer
//        Industry industry = industryRepository.findById(dto.getIndustryId())
//                .orElseThrow(() -> new RuntimeException("Không tìm thấy Industry"));
//        employer.setIndustry(industry);
//
//        employerRepository.save(employer);
//    }

//    public LoginResponseDTO loginEmployer(LoginRequestDTO dto) {
//        Account account = accountRepository.findByEmail(dto.getEmail())
//                .orElseThrow(() -> new RuntimeException("Thông tin đăng nhập không hợp lệ"));
//
//        // Kiểm tra mật khẩu
//        if (!passwordEncoder.matches(dto.getPassword(), account.getPassword())) {
//            throw new RuntimeException("Thông tin đăng nhập không hợp lệ");
//        }
//
//        // Tạo phản hồi đăng nhập
//        LoginResponseDTO response = new LoginResponseDTO();
//        response.setId(account.getId());
//        response.setEmail(account.getEmail());
//        response.setRole(account.getAccountType().name());
//
//        // Nếu có token (JWT), thêm vào response
//        response.setToken("sample-jwt-token"); // Placeholder cho JWT
//
//        return response;
//    }

    @Override
    public EmployerResponseDTO getEmployerById(Long id) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));
        return EmployerMapper.INSTANCE.toEmployerResponseDTO(employer);
    }

    @Override
    public List<EmployerResponseDTO> getAllEmployers() {
        List<Employer> employers = employerRepository.findAll();
        return employers.stream()
                .map(EmployerMapper.INSTANCE::toEmployerResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployerResponseDTO createEmployer(EmployerRequestDTO employerRequestDTO) {
        Employer employer = EmployerMapper.INSTANCE.toEmployerEntity(employerRequestDTO);

        // Thiết lập Industry cho Employer
        Industry industry = industryRepository.findById(employerRequestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Industry"));
        employer.setIndustry(industry);

        Employer savedEmployer = employerRepository.save(employer);
        return EmployerMapper.INSTANCE.toEmployerResponseDTO(savedEmployer);
    }

    @Override
    public EmployerResponseDTO updateEmployer(Long id, EmployerRequestDTO employerRequestDTO) {
        Employer employer = employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));

        // Cập nhật các thông tin của Employer
        employer.setCompanyName(employerRequestDTO.getCompanyName());
        employer.setCompanyPhone(employerRequestDTO.getCompanyPhone());
        employer.setCompanyAddress(employerRequestDTO.getCompanyAddress());
        employer.setCompanyWebsite(employerRequestDTO.getCompanyWebsite());
        employer.setCompanyDescription(employerRequestDTO.getCompanyDescription());
        employer.setLocation(employerRequestDTO.getLocation());

        Industry industry = industryRepository.findById(employerRequestDTO.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Industry"));
        employer.setIndustry(industry);

        Employer updatedEmployer = employerRepository.save(employer);
        return EmployerMapper.INSTANCE.toEmployerResponseDTO(updatedEmployer);
    }

    @Override
    public void deleteEmployer(Long id) {
        if (!employerRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy Employer");
        }
        employerRepository.deleteById(id);
    }

    @Override
    public List<SubscriptionResponseDTO> getEmployerSubscriptions(Long employerId) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));

        return employer.getSubscriptions().stream()
                .map(SubscriptionMapper.INSTANCE::toSubscriptionResponseDTO)
                .collect(Collectors.toList());
    }
}
