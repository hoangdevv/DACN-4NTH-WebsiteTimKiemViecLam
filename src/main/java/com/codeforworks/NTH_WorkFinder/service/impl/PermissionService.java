package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.permission.PermissionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.permission.PermissionResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.PermissionMapper;
import com.codeforworks.NTH_WorkFinder.model.Permission;
import com.codeforworks.NTH_WorkFinder.repository.PermissionRepository;
import com.codeforworks.NTH_WorkFinder.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public PermissionResponseDTO createPermission(PermissionRequestDTO permissionRequestDTO) {
        Permission permission = PermissionMapper.INSTANCE.toPermissionEntity(permissionRequestDTO);
        Permission savedPermission = permissionRepository.save(permission);
        return PermissionMapper.INSTANCE.toPermissionResponseDTO(savedPermission);
    }

    @Override
    public PermissionResponseDTO getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        return PermissionMapper.INSTANCE.toPermissionResponseDTO(permission);
    }

    @Override
    public List<PermissionResponseDTO> getAllPermissions() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(PermissionMapper.INSTANCE::toPermissionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionResponseDTO updatePermission(Long id, PermissionRequestDTO permissionRequestDTO) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        permission.setPermissionName(permissionRequestDTO.getPermissionName());
        permission.setDescription(permissionRequestDTO.getDescription());

        Permission updatedPermission = permissionRepository.save(permission);
        return PermissionMapper.INSTANCE.toPermissionResponseDTO(updatedPermission);
    }

    @Override
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new RuntimeException("Permission not found");
        }
        permissionRepository.deleteById(id);
    }
}
