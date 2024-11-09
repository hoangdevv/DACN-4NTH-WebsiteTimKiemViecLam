package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.permission.PermissionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.permission.PermissionResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Permission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PermissionMapper {
    PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

    Permission toPermissionEntity(PermissionRequestDTO dto);

    PermissionResponseDTO toPermissionResponseDTO(Permission permission);
}