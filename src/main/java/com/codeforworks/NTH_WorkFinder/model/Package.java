package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "package")
public class Package extends Base{

    private String packageName;

    @Column(nullable = false)
    private Integer duration; // Thời hạn của gói tính theo số ngày

    private Double price;

    @OneToMany(mappedBy = "aPackage")
    private Set<PackagePermission> packagePermissions;

    @OneToMany(mappedBy = "aPackage")
    private Set<Subscription> subscriptions;

}
