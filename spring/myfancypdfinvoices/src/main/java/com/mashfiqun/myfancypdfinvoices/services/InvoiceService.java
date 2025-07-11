package com.mashfiqun.myfancypdfinvoices.services;

import com.mashfiqun.myfancypdfinvoices.model.Invoice;
import com.mashfiqun.myfancypdfinvoices.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserService userService;
    private final JdbcTemplate jdbcTemplate;
    private final String cdnUrl;
    public InvoiceService(UserService userService, JdbcTemplate jdbcTemplate, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.jdbcTemplate = jdbcTemplate;
        this.cdnUrl = cdnUrl;
    }

    @Transactional
    public List<Invoice> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        return jdbcTemplate.query("select id, user_id, pdf_url, amount from invoices", (resultSet, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setPdfUrl(resultSet.getString("pdf_url"));
            invoice.setUserId(resultSet.getString("user_id"));
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF Template from S3...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Deleting downloaded templates...");
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        String generatedPdfUrl = cdnUrl + "/invoice/sample.pdf";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into invoices (user_id, pdf_url, amount) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, userId);
            ps.setObject(2, generatedPdfUrl);
            ps.setObject(3, amount);
            return ps;
        }, keyHolder);
        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString() : null;
        Invoice invoice = new Invoice();
        invoice.setId(uuid);
        invoice.setUserId(userId);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        return invoice;
    }
}
