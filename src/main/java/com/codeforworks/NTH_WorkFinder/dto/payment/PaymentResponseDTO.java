package com.codeforworks.NTH_WorkFinder.dto.payment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentResponseDTO {
    private Long id;
    private Long subscriptionId;
    private Double amount;
    private Date paymentDate;
    private String transactionId;
    private String method;
    private String status;
}