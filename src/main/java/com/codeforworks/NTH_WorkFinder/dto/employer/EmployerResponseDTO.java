package com.codeforworks.NTH_WorkFinder.dto.employer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployerResponseDTO {
    private Long id;
    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String companyWebsite;
    private String companyDescription;
    private String location;
    private String industryName; // Tên ngành nghề
}