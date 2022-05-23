package com.lolucode.financeInvoice.controller;

import com.lolucode.financeInvoice.model.Invoice;
import com.lolucode.financeInvoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping//api/invoice
    public ResponseEntity<?> createNewInvoice(@RequestBody Invoice invoice) {
        System.out.println("Requested user id"+invoice.getUserId());
        return new ResponseEntity<>(invoiceService.createNewInvoice(invoice), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")//api/invoice/{userId}
    public ResponseEntity<?> getAllInvoicesOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(invoiceService.viewAllInvoicesOfUser(userId));
    }

    @GetMapping("/reference/{reference}")//api/invoice/reference/{reference}
    public ResponseEntity<?> getInvoiceByReference(@PathVariable String reference) {
        return ResponseEntity.ok(invoiceService.getInvoiceByReference(reference));
    }

    @PutMapping("/{reference}/pay")
    public ResponseEntity<?> pay(@PathVariable String reference) {
        return ResponseEntity.ok(invoiceService.pay(reference));
    }

    @DeleteMapping("/{reference}/cancel")
    public ResponseEntity<?> cancel(@PathVariable String reference) {
        return ResponseEntity.ok(invoiceService.cancel(reference));
    }

    @PutMapping
    public ResponseEntity<?> payInvoice(@RequestBody Invoice invoice) {
        return new ResponseEntity<>(invoiceService.processInvoice(invoice), HttpStatus.OK);
    }
}
