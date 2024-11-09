package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.job.JobRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobSearchCriteria;

import java.util.List;

public interface IJobService {

    JobResponseDTO getJobById(Long id);

    List<JobResponseDTO> getAllJobs();

    JobResponseDTO createJob(JobRequestDTO jobRequest);

    JobResponseDTO updateJob(Long id, JobRequestDTO jobRequestDTO);

    void deleteJob(Long id);

    List<JobResponseDTO> searchJobs(JobSearchCriteria criteria);
}
