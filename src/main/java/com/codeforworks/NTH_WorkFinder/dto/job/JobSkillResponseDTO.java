package com.codeforworks.NTH_WorkFinder.dto.job;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobSkillResponseDTO {
    private Long id;
    private Long jobId;
    private Long skillId;
    private String skillName;
    private String proficiencyLevel;
}
