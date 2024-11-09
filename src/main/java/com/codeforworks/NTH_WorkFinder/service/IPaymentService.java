package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.payment.PaymentRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.payment.PaymentResponseDTO;

import java.util.List;

public interface IPaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO);

    PaymentResponseDTO getPaymentById(Long id);

    List<PaymentResponseDTO> getAllPayments();

    PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentRequestDTO);

    void deletePayment(Long id);
}