package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.profession.ProfessionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.profession.ProfessionResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Profession;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfessionMapper {
    ProfessionMapper INSTANCE = Mappers.getMapper(ProfessionMapper.class);

    Profession toProfessionEntity(ProfessionRequestDTO dto);

    ProfessionResponseDTO toProfessionResponseDTO(Profession profession);
}