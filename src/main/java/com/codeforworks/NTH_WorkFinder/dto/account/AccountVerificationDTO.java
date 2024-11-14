package com.codeforworks.NTH_WorkFinder.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AccountVerificationDTO {
    private String email;
    private String verificationCode; // Thuộc tính để lưu mã xác thực tạm thời
    private LocalDateTime expiryTime;// Thời điểm tạo mã OTP
}
