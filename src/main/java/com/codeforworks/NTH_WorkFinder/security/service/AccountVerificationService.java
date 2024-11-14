package com.codeforworks.NTH_WorkFinder.security.service;

import com.codeforworks.NTH_WorkFinder.dto.account.AccountVerificationDTO;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountVerificationService {

    // Lưu trữ OTP tạm thời với email là key
    private final Map<String, AccountVerificationDTO> otpStorage = new ConcurrentHashMap<>();

    @Autowired
    private EmailService emailService;

    // Thêm OTP vào bộ nhớ tạm thời
    public void storeVerificationCode(String email, String verificationCode) {
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(2); // Hết hạn sau 2 phút
        AccountVerificationDTO verificationDTO = new AccountVerificationDTO(email, verificationCode, expiryTime);
        otpStorage.put(email, verificationDTO);
    }

    // Kiểm tra OTP
    public boolean verifyCode(String email, String code) {
        AccountVerificationDTO verificationDTO = otpStorage.get(email);

        if (verificationDTO == null) {
            throw new RuntimeException("Không tìm thấy mã xác minh");
        }

        // Kiểm tra mã xác minh và thời gian hết hạn
        if (!verificationDTO.getVerificationCode().equals(code)) {
            throw new RuntimeException("Mã xác minh không hợp lệ");
        }

        if (verificationDTO.getExpiryTime().isBefore(LocalDateTime.now())) {
            otpStorage.remove(email); // Xóa mã OTP đã hết hạn
            throw new RuntimeException("Mã xác minh đã hết hạn");
        }

        // Xóa OTP sau khi xác thực thành công
        otpStorage.remove(email);
        return true;
    }

    // Phương thức gửi lại mã OTP
    public void resendVerificationCode(String email) throws MessagingException {
        AccountVerificationDTO verificationDTO = otpStorage.get(email);

        if (verificationDTO == null || verificationDTO.getExpiryTime().isBefore(LocalDateTime.now())) {
            // Tạo mã OTP mới nếu mã hiện tại không tồn tại hoặc đã hết hạn
            String newVerificationCode = generateVerificationCode();
            LocalDateTime newExpiryTime = LocalDateTime.now().plusMinutes(2);
            verificationDTO = new AccountVerificationDTO(email, newVerificationCode, newExpiryTime);
            otpStorage.put(email, verificationDTO);

            // Gửi lại mã qua email
            emailService.sendVerificationCode(email, newVerificationCode);
        } else {
            throw new RuntimeException("Vui lòng chờ trước khi yêu cầu mã mới.");
        }
    }

    // Hàm tạo mã OTP ngẫu nhiên
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Tạo mã 6 chữ số
        return String.valueOf(code);
    }
}