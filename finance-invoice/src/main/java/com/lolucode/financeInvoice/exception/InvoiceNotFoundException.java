package com.lolucode.financeInvoice.exception;

public class InvoiceNotFoundException extends RuntimeException {

    public InvoiceNotFoundException(Long id) {
        super("could not find invoice " + id);
    }
    public InvoiceNotFoundException(String reference) {
        super("could not find invoice with reference " + reference);
    }

    public InvoiceNotFoundException() {
        super("Could not find invoice");
    }
}
