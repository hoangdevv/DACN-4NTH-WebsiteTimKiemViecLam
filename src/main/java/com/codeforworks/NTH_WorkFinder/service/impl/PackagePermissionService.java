package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.apackage.PackagePermissionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.apackage.PackagePermissionResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.PackagePermissionMapper;
import com.codeforworks.NTH_WorkFinder.model.Package;
import com.codeforworks.NTH_WorkFinder.model.PackagePermission;
import com.codeforworks.NTH_WorkFinder.model.Permission;
import com.codeforworks.NTH_WorkFinder.repository.PackagePermissionRepository;
import com.codeforworks.NTH_WorkFinder.repository.PackageRepository;
import com.codeforworks.NTH_WorkFinder.repository.PermissionRepository;
import com.codeforworks.NTH_WorkFinder.service.IPackagePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackagePermissionService implements IPackagePermissionService {

    @Autowired
    private PackagePermissionRepository packagePermissionRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public PackagePermissionResponseDTO createPackagePermission(PackagePermissionRequestDTO packagePermissionRequestDTO) {
        Package aPackage = packageRepository.findById(packagePermissionRequestDTO.getPackageId())
                .orElseThrow(() -> new RuntimeException("Package not found"));

        Permission permission = permissionRepository.findById(packagePermissionRequestDTO.getPermissionId())
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        PackagePermission packagePermission = new PackagePermission();
        packagePermission.setPackageEntity(aPackage);
        packagePermission.setPermission(permission);
        packagePermission.setValue(packagePermissionRequestDTO.getValue());

        PackagePermission savedPackagePermission = packagePermissionRepository.save(packagePermission);
        return PackagePermissionMapper.INSTANCE.toPackagePermissionResponseDTO(savedPackagePermission);
    }

    @Override
    public PackagePermissionResponseDTO updatePackagePermission(Long id, PackagePermissionRequestDTO packagePermissionRequestDTO) {
        PackagePermission packagePermission = packagePermissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PackagePermission not found"));

        if (packagePermissionRequestDTO.getPackageId() != null) {
            Package aPackage = packageRepository.findById(packagePermissionRequestDTO.getPackageId())
                    .orElseThrow(() -> new RuntimeException("Package not found"));
            packagePermission.setPackageEntity(aPackage);
        }

        if (packagePermissionRequestDTO.getPermissionId() != null) {
            Permission permission = permissionRepository.findById(packagePermissionRequestDTO.getPermissionId())
                    .orElseThrow(() -> new RuntimeException("Permission not found"));
            packagePermission.setPermission(permission);
        }

        packagePermission.setValue(packagePermissionRequestDTO.getValue());

        PackagePermission updatedPackagePermission = packagePermissionRepository.save(packagePermission);
        return PackagePermissionMapper.INSTANCE.toPackagePermissionResponseDTO(updatedPackagePermission);
    }

    @Override
    public void removePermissionFromPackage(Long packagePermissionId) {
        if (!packagePermissionRepository.existsById(packagePermissionId)) {
            throw new RuntimeException("PackagePermission not found");
        }
        packagePermissionRepository.deleteById(packagePermissionId);
    }

    @Override
    public List<PackagePermissionResponseDTO> getPermissionsByPackageId(Long packageId) {
        List<PackagePermission> packagePermissions = packagePermissionRepository.findByPackageEntity_Id(packageId);
        return packagePermissions.stream()
                .map(PackagePermissionMapper.INSTANCE::toPackagePermissionResponseDTO)
                .collect(Collectors.toList());
    }
}
