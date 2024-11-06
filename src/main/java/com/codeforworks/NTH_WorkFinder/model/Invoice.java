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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvoice;

    @OneToOne
    @JoinColumn(name = "id_payment", nullable = false)
    private Payment payment; // Liên kết 1-1 với Payment

    private String invoiceNumber; // Số hóa đơn

    private Double totalAmount;

    private Boolean isPaid; // Trạng thái thanh toán
}
