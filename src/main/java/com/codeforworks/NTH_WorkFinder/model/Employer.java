package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employers")
public class Employer extends Base{
    @Id
    @Column(name = "id_employer", nullable = false, length = 10)
    private String idEmployer;

    @Column(name = "user_id", length = 10)
    private String userId;

    @Column(name = "company_name", length = 45)
    private String companyName;

    @Column(name = "company_address", length = 45)
    private String companyAddress;

    @Column(name = "company_phone", length = 15)
    private String companyPhone;

    @Column(name = "company_description", columnDefinition = "TEXT")
    private String companyDescription;

}
