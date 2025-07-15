package com.mashfiqun.myfancypdfinvoices.springboot.service;

import com.mashfiqun.myfancypdfinvoices.springboot.model.Invoice;
import com.mashfiqun.myfancypdfinvoices.springboot.model.User;
import com.mashfiqun.myfancypdfinvoices.springboot.repository.InvoiceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final String cdnUrl;

    public InvoiceService(InvoiceRepository invoiceRepository, @Value("${cdn.url}") String cdnUrl) {

        this.cdnUrl = cdnUrl;
        this.invoiceRepository = invoiceRepository;
    }

    @PostConstruct
    public void init() {
        System.out.println("Downloading templates...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Deleting templates...");
    }

    @Transactional
    public Iterable<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        String generatedPdfUrl = cdnUrl + "/invoices/sample.pdf";
        Invoice invoice = new Invoice();
        invoice.setUserId(userId);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        return invoiceRepository.save(invoice);
    }
}
