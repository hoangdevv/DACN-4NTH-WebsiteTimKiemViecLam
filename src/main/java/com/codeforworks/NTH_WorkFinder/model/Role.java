package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends Base{
    @Id
    @Column(name = "id_role", nullable = false, length = 10)
    private String idRole;

    @Column(name = "role_name", length = 45)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
