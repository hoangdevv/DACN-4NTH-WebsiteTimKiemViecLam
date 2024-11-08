package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employer")
public class Employer extends Base{
    @Column(name = "code", unique = true, nullable = false, updatable = false)
    private String code;
    @PrePersist
    protected void onPersist() {
        if (this.code == null) {
            this.code = "EMP-" + String.format("%05d", this.getId()); // Ví dụ: CAND-00001
        }
    }
    @OneToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_industry", nullable = false)
    private Industry industry; // Ngành công nghiệp của nhà tuyển dụng

    @Column(name = "company_name", length = 45)
    private String companyName;

    private String companyAddress;
    private String companyWebsite;
    private String companyPhone;
    private String companyDescription;
    private String location;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs;

    @OneToMany(mappedBy = "employer")
    private Set<Subscription> subscriptions;
}
