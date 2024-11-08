package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceResponseDTO;

import java.util.List;

public interface IInvoiceService {
    InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO);
    InvoiceResponseDTO getInvoiceById(Long id);
    List<InvoiceResponseDTO> getAllInvoices();
    InvoiceResponseDTO markAsPaid(Long id);
    void deleteInvoice(Long id);
}
