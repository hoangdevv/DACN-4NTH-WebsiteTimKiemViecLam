package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.job.JobRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobMapper {
    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    JobResponseDTO toJobResponseDTO(Job job);

    Job toJobEntity(JobRequestDTO jobRequestDTO);
}