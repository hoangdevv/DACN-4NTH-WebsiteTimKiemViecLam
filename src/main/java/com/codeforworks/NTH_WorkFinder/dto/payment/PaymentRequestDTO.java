package com.codeforworks.NTH_WorkFinder.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentRequestDTO {
    private Long subscriptionId; // ID của Subscription liên kết
    private Double amount;
    private Date paymentDate;
    private String transactionId; // Mã giao dịch thanh toán
    private String method;
    private String status;
}