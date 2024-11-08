package com.codeforworks.NTH_WorkFinder.dto.apackage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PackageResponseDTO {
    private Long id;
    private String packageName;
    private Integer duration;
    private Double price;
}