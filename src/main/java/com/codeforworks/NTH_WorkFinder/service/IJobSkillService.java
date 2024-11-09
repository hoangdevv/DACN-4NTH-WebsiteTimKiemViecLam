package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillResponseDTO;

import java.util.List;

public interface IJobSkillService {

    JobSkillResponseDTO createJobSkill(JobSkillRequestDTO jobSkillRequestDTO);

    JobSkillResponseDTO getJobSkillById(Long id);

    List<JobSkillResponseDTO> getJobSkillsByJobId(Long jobId);

    JobSkillResponseDTO updateJobSkill(Long id, JobSkillRequestDTO jobSkillRequestDTO);

    void deleteJobSkill(Long id);
}
