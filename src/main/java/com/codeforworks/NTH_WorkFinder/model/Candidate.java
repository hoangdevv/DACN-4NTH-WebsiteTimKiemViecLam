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
@Table(name = "candidate")
public class Candidate extends Base{
    @Id
    @Column(name = "id_candidate", nullable = false, length = 10)
    private String idCandidate;

    @OneToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    private String phone;
    private String address;
    private Date birthday;
    private Boolean sex;
    private String location;
    private String avatar;
    private String attachedFile;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;// Danh sách đơn ứng tuyển của ứng viên


    @OneToMany(mappedBy = "candidate")
    private List<CandidateSkill> candidateSkills;
}
