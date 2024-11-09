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
@Table(name = "invoice")
public class Invoice extends Base{
    @Column(name = "code", unique = true, nullable = false, updatable = false)
    private String code;
    @PrePersist
    protected void onPersist() {
        if (this.code == null) {
            this.code = "HD-" + String.format("%05d", this.getId());
        }
    }

    @OneToOne
    @JoinColumn(name = "id_payment", nullable = false)
    private Payment payment; // Liên kết 1-1 với Payment

    private Double totalAmount;

    private Boolean isPaid; // Trạng thái thanh toán
}
