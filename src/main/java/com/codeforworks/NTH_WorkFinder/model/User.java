package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends Base{

    @OneToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    @OneToOne(mappedBy = "user")
    private Candidate candidate;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;
}
