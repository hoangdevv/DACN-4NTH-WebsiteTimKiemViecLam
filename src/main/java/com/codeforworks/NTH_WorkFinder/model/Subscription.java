package com.codeforworks.NTH_WorkFinder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {


    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Package aPackage;

    private Date startDate; // Ngày bắt đầu sử dụng gói
    private Date endDate;   // Ngày kết thúc sử dụng gói

    private Boolean isActive; // Trạng thái của gói

    @OneToMany(mappedBy = "subscription")
    private List<Payment> payments; // Mối quan hệ 1-nhiều với Payment
}
