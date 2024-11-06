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
    @Id
    @Column(name = "id_employer", nullable = false, length = 10)
    private String idEmployer;

    @OneToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @Column(name = "company_name", length = 45)
    private String companyName;

    private String companyAddress;
    private String companyWebsite;
    private String companyPhone;
    private String companyDescription;

    @OneToMany(mappedBy = "employer")
    private List<Job> jobs;

    @OneToMany(mappedBy = "employer")
    private Set<Subscription> subscriptions;
}
