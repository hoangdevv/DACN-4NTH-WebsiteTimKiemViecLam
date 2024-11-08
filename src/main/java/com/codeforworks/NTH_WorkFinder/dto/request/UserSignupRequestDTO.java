package com.codeforworks.NTH_WorkFinder.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDTO {
    private String fullName;
    private String phone;
    private String email;
    private String password;
    private String confirmPassword;

}
