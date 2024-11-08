package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    InvoiceResponseDTO toInvoiceResponseDTO(Invoice invoice);

    Invoice toInvoiceEntity(InvoiceRequestDTO invoiceRequestDTO);
}
