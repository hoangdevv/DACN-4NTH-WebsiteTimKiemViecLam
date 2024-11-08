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
    @Column(name = "code", unique = true, nullable = false, updatable = false)
    private String code;
    @PrePersist
    protected void onPersist() {
        if (this.code == null) {
            this.code = "CAND-" + String.format("%05d", this.getId());
        }
    }

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
