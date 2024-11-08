package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.apackage.PackageRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.apackage.PackageResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Package;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PackageMapper {
    PackageMapper INSTANCE = Mappers.getMapper(PackageMapper.class);

    PackageResponseDTO toPackageResponseDTO(Package aPackage);
    Package toPackageEntity(PackageRequestDTO packageRequestDTO);
}