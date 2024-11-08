package com.codeforworks.NTH_WorkFinder.dto.candidate;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CandidateRequestDTO {
    private Long userId;
    private String phone;
    private String address;
    private Date birthday;
    private Boolean sex;
    private String location;
    private String avatar;
    private String attachedFile;
}