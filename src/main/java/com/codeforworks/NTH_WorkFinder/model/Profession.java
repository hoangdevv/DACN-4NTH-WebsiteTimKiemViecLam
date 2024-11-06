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
@Table(name = "profession")
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfession;

    private String name; // Tên ngành nghề
    private String description;

    @ManyToOne
    @JoinColumn(name = "industry_id", nullable = false)
    private Industry industry;

    @OneToMany(mappedBy = "profession")
    private List<Job> jobs;
}
