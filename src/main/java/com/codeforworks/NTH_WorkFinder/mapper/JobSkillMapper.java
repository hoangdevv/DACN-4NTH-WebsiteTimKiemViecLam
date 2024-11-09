package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.job.JobSkillResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.JobSkill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JobSkillMapper {
    JobSkillMapper INSTANCE = Mappers.getMapper(JobSkillMapper.class);

    JobSkill toJobSkillEntity(JobSkillRequestDTO dto);

    JobSkillResponseDTO toJobSkillResponseDTO(JobSkill jobSkill);
}
