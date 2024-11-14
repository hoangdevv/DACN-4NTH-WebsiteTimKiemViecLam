package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.invoice.InvoiceResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.InvoiceMapper;
import com.codeforworks.NTH_WorkFinder.model.Invoice;
import com.codeforworks.NTH_WorkFinder.model.Payment;
import com.codeforworks.NTH_WorkFinder.repository.InvoiceRepository;
import com.codeforworks.NTH_WorkFinder.repository.PaymentRepository;
import com.codeforworks.NTH_WorkFinder.service.IInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService implements IInvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO invoiceRequestDTO) {
        Invoice invoice = new Invoice();

        // Lấy Payment dựa trên ID từ invoiceRequestDTO
        Payment payment = paymentRepository.findById(invoiceRequestDTO.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Payment"));
        if (payment.getStatus() != Payment.PaymentStatus.COMPLETED) {
            throw new RuntimeException("Payment chưa hoàn tất. Không thể tạo Hóa đơn.");
        }

        // Thiết lập Payment và các thuộc tính của Invoice
        invoice.setPayment(payment);
        invoice.setTotalAmount(payment.getAmount()); // Lấy amount từ Payment
        invoice.setIsPaid(true);

        Invoice savedInvoice = invoiceRepository.save(invoice);

        // Thiết lập mã code duy nhất cho Invoice
        savedInvoice.setCode("APP-" + String.format("%05d", savedInvoice.getId()));
        savedInvoice = invoiceRepository.save(savedInvoice);

        return InvoiceMapper.INSTANCE.toInvoiceResponseDTO(savedInvoice);
    }

    @Override
    public InvoiceResponseDTO getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Hóa đơn"));
        return InvoiceMapper.INSTANCE.toInvoiceResponseDTO(invoice);
    }

    @Override
    public List<InvoiceResponseDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(InvoiceMapper.INSTANCE::toInvoiceResponseDTO)
                .collect(Collectors.toList());
    }
    //đánh dấu hóa đơn đã thanh toán,Tránh thanh toán lặp lại,Quản lý trạng thái hóa đơn
    @Override
    public InvoiceResponseDTO markAsPaid(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Hóa đơn"));
        if (invoice.getIsPaid()) {
            throw new RuntimeException("Hóa đơn đã được đánh dấu là đã thanh toán.");
        }
        // Đánh dấu hóa đơn là đã thanh toán nếu Payment status là COMPLETED
        if (invoice.getPayment().getStatus() == Payment.PaymentStatus.COMPLETED) {
            invoice.setIsPaid(true);
            Invoice updatedInvoice = invoiceRepository.save(invoice);
            return InvoiceMapper.INSTANCE.toInvoiceResponseDTO(updatedInvoice);
        } else {
            throw new RuntimeException("Thanh toán vẫn chưa hoàn tất. Không thể đánh dấu hóa đơn là đã thanh toán.");
        }
    }

    @Override
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy Invoice");
        }
        invoiceRepository.deleteById(id);
    }
}
