package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @Column(name = "id_job", nullable = false, length = 10)
    private String idJob;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @Column(name = "title", length = 45)
    private String title;

    @Column(name = "location", length = 45)
    private String location;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "experience_level", columnDefinition = "ENUM")
    private String experienceLevel;

    @Column(name = "job_type", columnDefinition = "ENUM")
    private String jobType;

}
