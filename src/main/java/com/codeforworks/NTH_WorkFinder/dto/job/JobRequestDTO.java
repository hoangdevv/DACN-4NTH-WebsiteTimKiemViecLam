package com.codeforworks.NTH_WorkFinder.dto.job;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JobRequestDTO {
    private String title;
    private String description;
    private Long employerId;
    private Long professionId;
    private Long industryId;
    private String location;
    private Long salary;
    private Date expiryDate;
    private String jobLevel;
    private String experienceLevel;
    private String educationLevel;
    private String jobType;
}
