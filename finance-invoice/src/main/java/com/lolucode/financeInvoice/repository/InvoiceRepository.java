package com.lolucode.financeInvoice.repository;

import com.lolucode.financeInvoice.model.Invoice;
import com.lolucode.financeInvoice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findInvoiceByReference(String reference);

    List<Invoice> findAllByUserId(Long userId);


    List<Invoice> findInvoiceByUserIdAndStatus(Long userId, Status status);

    List<Invoice> findInvoiceByAccountIdAndStatus(Long userId, Status status);


}
