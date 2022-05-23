package com.lolucode.apigateway.controller;

import com.lolucode.apigateway.request.FinanceInvoiceServiceRequest;
import com.lolucode.apigateway.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/invoices")//pre-path
public class FinanceInvoiceController {

    @Autowired
    private FinanceInvoiceServiceRequest financeInvoiceServiceRequest;

    @PostMapping//gateway/invoices
    public ResponseEntity<?> createNewInvoice(@RequestBody Object newInvoice) {
        return new ResponseEntity<>(financeInvoiceServiceRequest.createNewInvoice(newInvoice), HttpStatus.CREATED);
    }

    @GetMapping //gateway/invoices
    public ResponseEntity<?> getAllInvoicesOfAuthorizedUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(financeInvoiceServiceRequest.getAllInvoicesOfUser(userPrincipal.getId()));
    }

    @GetMapping("/{reference}")//gateway/invoices/{reference}
    public ResponseEntity<?> getInvoiceByReference(@PathVariable(value = "reference") String reference) {
        ;
        return new ResponseEntity<>(financeInvoiceServiceRequest.getInvoiceByReference(reference),HttpStatus.OK);
    }

    @PutMapping("/{reference}/pay")//gateway/invoices/{reference}/pay
    public ResponseEntity<?> pay(@PathVariable(value = "reference") String reference) {
        return new ResponseEntity<>(financeInvoiceServiceRequest.pay(reference)
        ,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("reference/{reference}/cancel")//gateway/invoices/{reference}/cancel
    public ResponseEntity<?> cancel(@PathVariable(value = "reference") String reference) {
        return new ResponseEntity<>(financeInvoiceServiceRequest.cancel(reference),HttpStatus.OK);
    }

}
