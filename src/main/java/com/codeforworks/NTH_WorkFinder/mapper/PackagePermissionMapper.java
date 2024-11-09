package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.apackage.PackagePermissionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.apackage.PackagePermissionResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.PackagePermission;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PackagePermissionMapper {
    PackagePermissionMapper INSTANCE = Mappers.getMapper(PackagePermissionMapper.class);

    PackagePermission toPackagePermissionEntity(PackagePermissionRequestDTO dto);

    PackagePermissionResponseDTO toPackagePermissionResponseDTO(PackagePermission packagePermission);
}
