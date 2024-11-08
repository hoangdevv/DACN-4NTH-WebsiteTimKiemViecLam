package com.codeforworks.NTH_WorkFinder.model;

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
//    @Id
//    @Column(name = "id_account",  nullable = false,length = 10)
//    private String id;

    @Email(message = "Email phải hợp lệ")
    @Column(nullable = false, unique = true) // Email không được để trống và phải là duy nhất
    private String email;

    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least 8 characters, including uppercase, lowercase, number, and special character"
    ) // Mật khẩu có ít nhất 8 ký tự, bao gồm chữ thường, chữ hoa, số và ký tự đặc biệt
    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", length = 45)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    private Boolean status;

    @ManyToMany
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
    private Employer employer;

    public enum AccountType {
        USER,
        ADMIN,
        EMPLOYER
    }
}
