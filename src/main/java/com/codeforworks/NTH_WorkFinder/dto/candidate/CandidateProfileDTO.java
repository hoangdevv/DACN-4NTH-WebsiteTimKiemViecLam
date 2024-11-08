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
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private Date birthday;
    private Boolean sex;
    private String location;
    private String avatar;
    private String attachedFile;
    private List<String> skills; // Danh sách kỹ năng của ứng viên
    private List<ApplicationResponseDTO> applications; // Danh sách đơn ứng tuyển của ứng viên
}
