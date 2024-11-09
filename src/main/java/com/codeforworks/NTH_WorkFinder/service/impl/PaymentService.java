package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.payment.PaymentRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.payment.PaymentResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.PaymentMapper;
import com.codeforworks.NTH_WorkFinder.model.Payment;
import com.codeforworks.NTH_WorkFinder.model.Subscription;
import com.codeforworks.NTH_WorkFinder.repository.PaymentRepository;
import com.codeforworks.NTH_WorkFinder.repository.SubscriptionRepository;
import com.codeforworks.NTH_WorkFinder.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO) {
        Subscription subscription = subscriptionRepository.findById(paymentRequestDTO.getSubscriptionId())
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        Payment payment = PaymentMapper.INSTANCE.toPaymentEntity(paymentRequestDTO);
        payment.setSubscription(subscription);

        Payment savedPayment = paymentRepository.save(payment);
        return PaymentMapper.INSTANCE.toPaymentResponseDTO(savedPayment);
    }

    @Override
    public PaymentResponseDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return PaymentMapper.INSTANCE.toPaymentResponseDTO(payment);
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(PaymentMapper.INSTANCE::toPaymentResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PaymentResponseDTO updatePayment(Long id, PaymentRequestDTO paymentRequestDTO) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Subscription subscription = subscriptionRepository.findById(paymentRequestDTO.getSubscriptionId())
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        payment.setSubscription(subscription);
        payment.setAmount(paymentRequestDTO.getAmount());
        payment.setPaymentDate(paymentRequestDTO.getPaymentDate());
        payment.setTransactionId(paymentRequestDTO.getTransactionId());
        payment.setMethod(Payment.PaymentMethod.valueOf(paymentRequestDTO.getMethod()));
        payment.setStatus(Payment.PaymentStatus.valueOf(paymentRequestDTO.getStatus()));

        Payment updatedPayment = paymentRepository.save(payment);
        return PaymentMapper.INSTANCE.toPaymentResponseDTO(updatedPayment);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }
}
