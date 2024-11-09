package com.codeforworks.NTH_WorkFinder.dto.apackage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackagePermissionResponseDTO {
    private Long id;
    private Long packageId;
    private Long permissionId;
    private String permissionName;
    private String value;
}
