package com.codeforworks.NTH_WorkFinder.dto.interview;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InterviewResponseDTO {
    private Long id;
    private Long applicationId;
    private Date interviewDate;
    private String location;
    private String status;
    private String feedback;
}
