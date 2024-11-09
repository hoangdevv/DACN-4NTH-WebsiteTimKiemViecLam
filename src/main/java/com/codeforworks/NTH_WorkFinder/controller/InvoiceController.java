package com.codeforworks.NTH_WorkFinder.controller;

import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceResponseDTO;
import com.codeforworks.NTH_WorkFinder.service.impl.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // Tạo một hóa đơn mới
    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody InvoiceRequestDTO invoiceRequestDTO) {
        InvoiceResponseDTO createdInvoice = invoiceService.createInvoice(invoiceRequestDTO);
        return ResponseEntity.ok(createdInvoice);
    }

    // Lấy thông tin một hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceResponseDTO invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    // Lấy danh sách tất cả các hóa đơn
    @GetMapping
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {
        List<InvoiceResponseDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    // Đánh dấu hóa đơn đã thanh toán
    @PutMapping("/mark-as-paid/{id}")
    public ResponseEntity<InvoiceResponseDTO> markAsPaid(@PathVariable Long id) {
        InvoiceResponseDTO updatedInvoice = invoiceService.markAsPaid(id);
        return ResponseEntity.ok(updatedInvoice);
    }

    // Xóa hóa đơn theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
