package com.codeforworks.NTH_WorkFinder.dto.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequestDTO {
    private Long candidateId;
    private Long jobId;
    private String coverLetter;
}