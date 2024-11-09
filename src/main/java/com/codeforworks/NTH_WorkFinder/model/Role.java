package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role extends Base{

    private String roleName;
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts;
}
