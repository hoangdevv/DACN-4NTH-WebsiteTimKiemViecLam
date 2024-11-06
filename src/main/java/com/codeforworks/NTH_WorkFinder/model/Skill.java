package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill")
public class Skill extends Base{
    @Id
    @Column(name = "id_skill", length = 10)
    private String idSkill;

    private String skillName;

    @OneToMany(mappedBy = "skill")
    private List<JobSkill> jobSkills;

    @OneToMany(mappedBy = "skill")
    private Set<CandidateSkill> candidateSkills;
}
