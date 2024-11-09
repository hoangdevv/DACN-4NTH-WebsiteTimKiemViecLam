package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.apackage.PackagePermissionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.apackage.PackagePermissionResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.IPackagePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/package-permissions")
public class PackagePermissionController {

    @Autowired
    private IPackagePermissionService packagePermissionService;

    @PostMapping
    public ResponseEntity<PackagePermissionResponseDTO> createPackagePermission(
            @RequestBody PackagePermissionRequestDTO packagePermissionRequestDTO) {
        PackagePermissionResponseDTO createdPackagePermission =
                packagePermissionService.createPackagePermission(packagePermissionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPackagePermission);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PackagePermissionResponseDTO> updatePackagePermission(
            @PathVariable Long id,
            @RequestBody PackagePermissionRequestDTO packagePermissionRequestDTO) {
        PackagePermissionResponseDTO updatedPackagePermission =
                packagePermissionService.updatePackagePermission(id, packagePermissionRequestDTO);
        return ResponseEntity.ok(updatedPackagePermission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePermissionFromPackage(@PathVariable Long id) {
        packagePermissionService.removePermissionFromPackage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<PackagePermissionResponseDTO>> getPermissionsByPackageId(@PathVariable Long packageId) {
        List<PackagePermissionResponseDTO> packagePermissions =
                packagePermissionService.getPermissionsByPackageId(packageId);
        return ResponseEntity.ok(packagePermissions);
    }
}
