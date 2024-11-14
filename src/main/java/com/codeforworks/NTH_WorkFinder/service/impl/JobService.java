package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.job.JobRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobResponseDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobSearchCriteria;
import com.codeforworks.NTH_WorkFinder.mapper.JobMapper;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import com.codeforworks.NTH_WorkFinder.model.Industry;
import com.codeforworks.NTH_WorkFinder.model.Job;
import com.codeforworks.NTH_WorkFinder.model.Profession;
import com.codeforworks.NTH_WorkFinder.repository.EmployerRepository;
import com.codeforworks.NTH_WorkFinder.repository.IndustryRepository;
import com.codeforworks.NTH_WorkFinder.repository.JobRepository;
import com.codeforworks.NTH_WorkFinder.repository.ProfessionRepository;
import com.codeforworks.NTH_WorkFinder.service.IJobService;
import com.codeforworks.NTH_WorkFinder.specification.JobSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService implements IJobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private IndustryRepository industryRepository;

    @Override
    public JobResponseDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Job"));
        return JobMapper.INSTANCE.toJobResponseDTO(job);
    }
    @Override
    public List<JobResponseDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(JobMapper.INSTANCE::toJobResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public JobResponseDTO createJob(JobRequestDTO jobRequestDTO) {
        // Chuyển đổi các trường cơ bản
        Job job = JobMapper.INSTANCE.toJobEntity(jobRequestDTO);

        // Thiết lập các mối quan hệ bằng cách lấy từ database
        Employer employer = employerRepository.findById(jobRequestDTO.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Employer"));
        job.setEmployer(employer);

        if (jobRequestDTO.getProfessionId() != null) {
            Profession profession = professionRepository.findById(jobRequestDTO.getProfessionId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Profession"));
            job.setProfession(profession);
        }

        if (jobRequestDTO.getIndustryId() != null) {
            Industry industry = industryRepository.findById(jobRequestDTO.getIndustryId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy Industry"));
            job.setIndustry(industry);
        }

        // Lưu Job và chuyển đổi kết quả sang DTO để trả về
        Job savedJob = jobRepository.save(job);

        // Thiết lập mã code duy nhất cho Job
        savedJob.setCode("APP-" + String.format("%05d", savedJob.getId()));
        savedJob = jobRepository.save(savedJob);

        return JobMapper.INSTANCE.toJobResponseDTO(savedJob);
    }
    @Override
    public JobResponseDTO updateJob(Long id, JobRequestDTO jobRequestDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Cập nhật các trường cơ bản
        job.setTitle(jobRequestDTO.getTitle());
        job.setDescription(jobRequestDTO.getDescription());
        job.setLocation(jobRequestDTO.getLocation());
        job.setSalary(jobRequestDTO.getSalary());
        job.setExpiryDate(jobRequestDTO.getExpiryDate());

        // Cập nhật các trường enum
        try {
            job.setJobLevel(Job.JobLevel.valueOf(jobRequestDTO.getJobLevel()));
            job.setExperienceLevel(Job.ExperienceLevel.valueOf(jobRequestDTO.getExperienceLevel()));
            job.setEducationLevel(Job.EducationLevel.valueOf(jobRequestDTO.getEducationLevel()));
            job.setJobType(Job.JobType.valueOf(jobRequestDTO.getJobType()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Giá trị không hợp lệ cho JobLevel, ExperienceLevel, EducationLevel hoặc JobType");
        }

        // Thiết lập lại mối quan hệ Employer, Profession, Industry
        Employer employer = employerRepository.findById(jobRequestDTO.getEmployerId())
                .orElseThrow(() -> new RuntimeException("Employer not found"));
        job.setEmployer(employer);

        if (jobRequestDTO.getProfessionId() != null) {
            Profession profession = professionRepository.findById(jobRequestDTO.getProfessionId())
                    .orElseThrow(() -> new RuntimeException("Profession not found"));
            job.setProfession(profession);
        }

        if (jobRequestDTO.getIndustryId() != null) {
            Industry industry = industryRepository.findById(jobRequestDTO.getIndustryId())
                    .orElseThrow(() -> new RuntimeException("Industry not found"));
            job.setIndustry(industry);
        }

        Job updatedJob = jobRepository.save(job);
        return JobMapper.INSTANCE.toJobResponseDTO(updatedJob);
    }
    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found");
        }
        jobRepository.deleteById(id);
    }

    @Override
    public List<JobResponseDTO> searchJobs(JobSearchCriteria criteria) {
        JobSpecification jobSpecification = new JobSpecification(criteria);
        List<Job> jobs = jobRepository.findAll(jobSpecification);

        return jobs.stream()
                .map(JobMapper.INSTANCE::toJobResponseDTO)
                .collect(Collectors.toList());
    }
}
