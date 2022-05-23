package com.lolucode.financeInvoice.service;

import com.lolucode.financeInvoice.dto.InvoiceDto;
import com.lolucode.financeInvoice.model.Invoice;

import java.util.List;

public interface InvoiceService {

    InvoiceDto createNewInvoice(Invoice invoice);

    List<InvoiceDto> viewAllInvoicesOfUser(Long userId);

    Invoice getInvoiceByReference(String reference);

    Invoice pay(String reference);

    Invoice cancel(String reference);

    Invoice processPayment(String reference);

    Invoice payInvoice(Invoice invoice);

    Invoice processInvoice(Invoice invoice);
}
