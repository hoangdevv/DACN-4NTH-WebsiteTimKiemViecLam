package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin extends Base{
//    @Id
//    @Column(name = "id_admin",  nullable = false,length = 10)
//    private String idAdmin;

    @OneToOne
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;
}
