package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.employer.EmployerResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Employer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployerMapper {
    EmployerMapper INSTANCE = Mappers.getMapper(EmployerMapper.class);

    EmployerResponseDTO toEmployerResponseDTO(Employer employer);

    Employer toEmployerEntity(EmployerRequestDTO employerRequestDTO);
}