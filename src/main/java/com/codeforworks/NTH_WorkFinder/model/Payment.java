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
@Table(name = "payment")
public class Payment extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPayment;

    @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false)
    private Subscription subscription;

    private Double amount;

    private Date paymentDate;

    @OneToOne(mappedBy = "payment")
    private Invoice invoice;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String transactionId; // Mã giao dịch thanh toán

    public enum PaymentStatus {
        PENDING,
        COMPLETED,
        FAILED
    }
    public enum PaymentMethod {
        CREDIT_CARD,
        BANK_TRANSFER,
        PAYPAL,
        MOMO,
        CASH
    }
}
