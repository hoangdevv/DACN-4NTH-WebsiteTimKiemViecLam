package com.codeforworks.NTH_WorkFinder.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {
    private Long id;
    private String email;
    private String role;
    private String token;
}
