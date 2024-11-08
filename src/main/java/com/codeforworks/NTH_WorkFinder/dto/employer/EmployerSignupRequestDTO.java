package com.codeforworks.NTH_WorkFinder.dto.employer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerSignupRequestDTO {
    private String email;
    private String password;
    private String confirmPassword;

    // Thông tin công ty
    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String location;
    private Long industryId;
}
