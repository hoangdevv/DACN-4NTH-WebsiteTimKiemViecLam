package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.apackage.PackageRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.apackage.PackageResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.IPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    private IPackageService packageService;

    @PostMapping("/create")
    public ResponseEntity<PackageResponseDTO> createPackage(@RequestBody PackageRequestDTO packageRequestDTO) {
        PackageResponseDTO createdPackage = packageService.createPackage(packageRequestDTO);
        return ResponseEntity.ok(createdPackage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PackageResponseDTO> getPackageById(@PathVariable Long id) {
        PackageResponseDTO aPackage = packageService.getPackageById(id);
        return ResponseEntity.ok(aPackage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PackageResponseDTO>> getAllPackages() {
        List<PackageResponseDTO> packages = packageService.getAllPackages();
        return ResponseEntity.ok(packages);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PackageResponseDTO> updatePackage(@PathVariable Long id, @RequestBody PackageRequestDTO packageRequestDTO) {
        PackageResponseDTO updatedPackage = packageService.updatePackage(id, packageRequestDTO);
        return ResponseEntity.ok(updatedPackage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePackage(@PathVariable Long id) {
        packageService.deletePackage(id);
        return ResponseEntity.noContent().build();
    }
}
