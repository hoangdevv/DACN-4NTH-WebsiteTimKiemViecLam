package com.codeforworks.NTH_WorkFinder.dto.employer;

import com.codeforworks.NTH_WorkFinder.dto.job.JobResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployerProfileDTO {
    private Long id;
    private String companyName;
    private String companyPhone;
    private String companyAddress;
    private String companyWebsite;
    private String companyDescription;
    private String location;
    private String industryName; // Lĩnh vực công ty hoạt động
    private List<JobResponseDTO> jobs; // Danh sách công việc đã đăng tuyển
}
