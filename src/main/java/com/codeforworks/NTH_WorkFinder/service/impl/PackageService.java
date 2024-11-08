package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.apackage.PackageRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.apackage.PackageResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.PackageMapper;
import com.codeforworks.NTH_WorkFinder.model.Package;
import com.codeforworks.NTH_WorkFinder.repository.PackageRepository;
import com.codeforworks.NTH_WorkFinder.service.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService implements IPackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public PackageResponseDTO createPackage(PackageRequestDTO packageRequestDTO) {
        Package aPackage = PackageMapper.INSTANCE.toPackageEntity(packageRequestDTO);
        Package savedPackage = packageRepository.save(aPackage);
        return PackageMapper.INSTANCE.toPackageResponseDTO(savedPackage);
    }

    @Override
    public PackageResponseDTO getPackageById(Long id) {
        Package aPackage = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));
        return PackageMapper.INSTANCE.toPackageResponseDTO(aPackage);
    }

    @Override
    public List<PackageResponseDTO> getAllPackages() {
        List<Package> packages = packageRepository.findAll();
        return packages.stream()
                .map(PackageMapper.INSTANCE::toPackageResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PackageResponseDTO updatePackage(Long id, PackageRequestDTO packageRequestDTO) {
        Package aPackage = packageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found"));

        aPackage.setPackageName(packageRequestDTO.getPackageName());
        aPackage.setDuration(packageRequestDTO.getDuration());
        aPackage.setPrice(packageRequestDTO.getPrice());

        Package updatedPackage = packageRepository.save(aPackage);
        return PackageMapper.INSTANCE.toPackageResponseDTO(updatedPackage);
    }

    @Override
    public void deletePackage(Long id) {
        if (!packageRepository.existsById(id)) {
            throw new RuntimeException("Package not found");
        }
        packageRepository.deleteById(id);
    }
}
