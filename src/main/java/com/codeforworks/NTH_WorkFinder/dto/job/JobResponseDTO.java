package com.codeforworks.NTH_WorkFinder.dto.job;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String employerName;
    private String professionName;
    private String industryName;
    private String location;
    private Long salary;
    private Date expiryDate;
    private String jobLevel;
    private String experienceLevel;
    private String educationLevel;
    private String jobType;
}
