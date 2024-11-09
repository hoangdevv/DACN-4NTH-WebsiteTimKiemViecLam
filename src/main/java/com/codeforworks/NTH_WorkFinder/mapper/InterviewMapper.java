package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.interview.InterviewResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Interview;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InterviewMapper {
    InterviewMapper INSTANCE = Mappers.getMapper(InterviewMapper.class);

    Interview toInterviewEntity(InterviewRequestDTO dto);

    InterviewResponseDTO toInterviewResponseDTO(Interview interview);
}
