package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.candidate.CandidateSkillResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.CandidateSkill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidateSkillMapper {
    CandidateSkillMapper INSTANCE = Mappers.getMapper(CandidateSkillMapper.class);

    CandidateSkill toCandidateSkillEntity(CandidateSkillRequestDTO dto);

    CandidateSkillResponseDTO toCandidateSkillResponseDTO(CandidateSkill candidateSkill);
}
