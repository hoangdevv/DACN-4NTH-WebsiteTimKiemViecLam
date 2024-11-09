package com.codeforworks.NTH_WorkFinder.dto.permission;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionResponseDTO {
    private Long id;
    private String permissionName;
    private String description;
}