package com.codeforworks.NTH_WorkFinder.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
    private String token;
}
