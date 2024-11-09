package com.codeforworks.NTH_WorkFinder.dto.candidate;

import com.codeforworks.NTH_WorkFinder.dto.application.ApplicationResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CandidateProfileDTO {

    private Long id;
    private String code;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Date birthday;
    private Boolean sex; // true for male, false for female
    private String location;
    private String avatar;
    private String attachedFile;
    private int experienceYears;
    private List<String> certifications;
    private List<String> workHistory;
    private List<ApplicationResponseDTO> applications; // Danh sách ứng tuyển của ứng viên
    private List<CandidateSkillResponseDTO> candidateSkills; // Các kỹ năng và mức độ thành thạo của ứng viên
}
