package com.codeforworks.NTH_WorkFinder.dto.job;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobSkillRequestDTO {
    private Long jobId;
    private Long skillId;
    private String proficiencyLevel; // BASIC, INTERMEDIATE, ADVANCED
}
