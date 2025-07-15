package com.mashfiqun.myfancypdfinvoices.springboot.web;

import com.mashfiqun.myfancypdfinvoices.springboot.dto.InvoiceDto;
import com.mashfiqun.myfancypdfinvoices.springboot.model.Invoice;
import com.mashfiqun.myfancypdfinvoices.springboot.repository.InvoiceRepository;
import com.mashfiqun.myfancypdfinvoices.springboot.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
public class InvoicesController {
    private final InvoiceRepository invoiceRepository;
    private InvoiceService invoiceService;
    public InvoicesController(InvoiceService invoiceService, InvoiceRepository invoiceRepository) {
        this.invoiceService = invoiceService;
        this.invoiceRepository = invoiceRepository;
    }

    @GetMapping("/invoices")
    public Iterable<Invoice> invoices() {
        return invoiceService.findAll();
    }

    @PostMapping("/invoices")
    public Invoice createInvoice(@Valid @RequestBody InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto.getUserId(), invoiceDto.getAmount());

    }
    @GetMapping("/invoices/user/{userId}")
    public Iterable<Invoice> findByUserId(@PathVariable String userId) {
        return invoiceRepository.findByUserId(userId);
    }
}
