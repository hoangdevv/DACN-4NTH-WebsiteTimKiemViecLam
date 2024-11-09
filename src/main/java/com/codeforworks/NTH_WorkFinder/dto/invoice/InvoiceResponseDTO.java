package com.codeforworks.NTH_WorkFinder.dto.invoice;

import com.codeforworks.NTH_WorkFinder.model.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InvoiceResponseDTO {
    private Long id;
    private String code;
    private Double totalAmount;
    private Boolean isPaid;
    private Date createdAt;
    private Long paymentId; // ID của Payment
    private String transactionId; // Mã giao dịch từ Payment
    private String paymentStatus;
    private String paymentMethod;
}
