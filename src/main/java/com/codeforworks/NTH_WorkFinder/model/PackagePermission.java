package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "package_permission")
public class PackagePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Package aPackage;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @Column(nullable = false)
    private String value; // Trường bổ sung để lưu giá trị tùy chỉnh cho quyền

    private Boolean isActive = true; // Trạng thái quyền

    private Date grantedDate; // Ngày cấp quyền
}
