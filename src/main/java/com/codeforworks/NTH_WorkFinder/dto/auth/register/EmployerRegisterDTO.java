package com.codeforworks.NTH_WorkFinder.dto.auth.register;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String location;
    private String industry;
}
