package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.payment.PaymentRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.payment.PaymentResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    Payment toPaymentEntity(PaymentRequestDTO dto);

    PaymentResponseDTO toPaymentResponseDTO(Payment payment);
}
