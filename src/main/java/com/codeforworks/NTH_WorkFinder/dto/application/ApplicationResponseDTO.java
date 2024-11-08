package com.codeforworks.NTH_WorkFinder.dto.application;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ApplicationResponseDTO {
    private Long id;
    private Long candidateId;
    private Long jobId;
    private String jobTitle;
    private String candidateName;
    private String coverLetter;
    private String status;
    private Date appliedDate;
}
