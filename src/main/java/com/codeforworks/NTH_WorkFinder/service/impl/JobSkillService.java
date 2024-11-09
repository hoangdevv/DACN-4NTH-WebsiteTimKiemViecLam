package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.JobSkillMapper;
import com.codeforworks.NTH_WorkFinder.model.Job;
import com.codeforworks.NTH_WorkFinder.model.JobSkill;
import com.codeforworks.NTH_WorkFinder.model.Skill;
import com.codeforworks.NTH_WorkFinder.repository.JobRepository;
import com.codeforworks.NTH_WorkFinder.repository.JobSkillRepository;
import com.codeforworks.NTH_WorkFinder.repository.SkillRepository;
import com.codeforworks.NTH_WorkFinder.service.IJobSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSkillService implements IJobSkillService {

    @Autowired
    private JobSkillRepository jobSkillRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public JobSkillResponseDTO createJobSkill(JobSkillRequestDTO jobSkillRequestDTO) {
        Job job = jobRepository.findById(jobSkillRequestDTO.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Skill skill = skillRepository.findById(jobSkillRequestDTO.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        JobSkill jobSkill = JobSkillMapper.INSTANCE.toJobSkillEntity(jobSkillRequestDTO);
        jobSkill.setJob(job);
        jobSkill.setSkill(skill);

        JobSkill savedJobSkill = jobSkillRepository.save(jobSkill);
        return JobSkillMapper.INSTANCE.toJobSkillResponseDTO(savedJobSkill);
    }

    @Override
    public JobSkillResponseDTO getJobSkillById(Long id) {
        JobSkill jobSkill = jobSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSkill not found"));
        return JobSkillMapper.INSTANCE.toJobSkillResponseDTO(jobSkill);
    }

    @Override
    public List<JobSkillResponseDTO> getJobSkillsByJobId(Long jobId) {
        List<JobSkill> jobSkills = jobSkillRepository.findByJobId(jobId);
        return jobSkills.stream()
                .map(JobSkillMapper.INSTANCE::toJobSkillResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobSkillResponseDTO updateJobSkill(Long id, JobSkillRequestDTO jobSkillRequestDTO) {
        JobSkill jobSkill = jobSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("JobSkill not found"));

        if (jobSkillRequestDTO.getJobId() != null) {
            Job job = jobRepository.findById(jobSkillRequestDTO.getJobId())
                    .orElseThrow(() -> new RuntimeException("Job not found"));
            jobSkill.setJob(job);
        }

        if (jobSkillRequestDTO.getSkillId() != null) {
            Skill skill = skillRepository.findById(jobSkillRequestDTO.getSkillId())
                    .orElseThrow(() -> new RuntimeException("Skill not found"));
            jobSkill.setSkill(skill);
        }

        jobSkill.setProficiencyLevel(JobSkill.ProficiencyLevel.valueOf(jobSkillRequestDTO.getProficiencyLevel()));

        JobSkill updatedJobSkill = jobSkillRepository.save(jobSkill);
        return JobSkillMapper.INSTANCE.toJobSkillResponseDTO(updatedJobSkill);
    }

    @Override
    public void deleteJobSkill(Long id) {
        if (!jobSkillRepository.existsById(id)) {
            throw new RuntimeException("JobSkill not found");
        }
        jobSkillRepository.deleteById(id);
    }
}
