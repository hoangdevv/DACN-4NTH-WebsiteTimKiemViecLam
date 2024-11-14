package com.codeforworks.NTH_WorkFinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_industry")
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
