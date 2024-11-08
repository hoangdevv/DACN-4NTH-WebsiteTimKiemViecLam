package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job extends Base {
    @Column(name = "code", unique = true, nullable = false, updatable = false)
    private String code;
    @PrePersist
    protected void onPersist() {
        if (this.code == null) {
            this.code = "JOB-" + String.format("%05d", this.getId());
        }
    }

    @ManyToOne
    @JoinColumn(name = "id_employer", nullable = false)
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "id_profession")
    private Profession profession;

    @ManyToOne
    @JoinColumn(name = "id_industry")
    private Industry industry;

    private String title;
    private String description;
    private String location;
    private Long salary;
    private Date expiryDate;

    @OneToMany(mappedBy = "job")
    private List<JobSkill> jobSkills;

    @OneToMany(mappedBy = "job")
    private List<Application> applications;

    @Enumerated(EnumType.STRING)
    private JobLevel jobLevel;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    private EducationLevel educationLevel;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    public enum JobLevel {
        Internship, //thực tập
        Graduated, //đã tốt nghiệp
        Personnel, //nhân viên
        Managerment, //quản lý
        President //giám đốc
    }

    public enum ExperienceLevel {
        NoExperience, //chưa kinh nghiệm
        Under1Year,
        Between12Year, //1-2năm
        Between25Year,
        Between510Year,
        Over10Year
    }

    public enum EducationLevel{
        HighSchool, //THPT
        University,
        Master, //thạc sĩ
        Doctor //tiến sĩ
    }

    public enum JobType{
        FullTime,
        PartTime,
        Seasonal //thời vụ
    }
}
