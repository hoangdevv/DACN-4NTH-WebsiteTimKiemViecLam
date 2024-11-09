package com.codeforworks.NTH_WorkFinder.dto.job;


import com.codeforworks.NTH_WorkFinder.model.Job;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobSearchCriteria {
    private String location;            // Địa điểm công việc
    private String experienceLevel; // Kinh nghiệm yêu cầu
    private String jobLevel;          // Cấp bậc công việc
    private String educationLevel; // Trình độ học vấn
    private String jobType;            // Loại hình công việc
}