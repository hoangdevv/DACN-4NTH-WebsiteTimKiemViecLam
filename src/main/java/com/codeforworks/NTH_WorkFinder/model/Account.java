package com.codeforworks.NTH_WorkFinder.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "account")
public class Account extends Base{

    @Email(message = "Email phải hợp lệ")
    @Column(nullable = false, unique = true) // Email không được để trống và phải là duy nhất
    private String email;

//    @Pattern(
//            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//            message = "Mật khẩu có ít nhất 8 ký tự, bao gồm chữ thường, chữ hoa, số và ký tự đặc biệt"
//    )
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    private Boolean status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToOne(mappedBy = "account")
    private Admin admin;

    @OneToOne(mappedBy = "account")
    @JsonIgnore
    private Employer employer;

    public enum AccountType {
        USER,
        ADMIN,
        EMPLOYER
    }
}
