package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "industry")
public class Industry extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIndustry;

    private String name;
    private String description;

    @OneToMany(mappedBy = "industry")
    private List<Profession> professions; // Danh sách các nghề nghiệp thuộc ngành này

    @OneToMany(mappedBy = "industry")
    private List<Job> jobs;
}