package com.codeforworks.NTH_WorkFinder.repository;

import com.codeforworks.NTH_WorkFinder.model.PackagePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackagePermissionRepository extends JpaRepository<PackagePermission, Long> {
    List<PackagePermission> findByPackageEntity_Id(Long packageId);
}
