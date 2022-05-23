package com.lolucode.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "invoice-service", //microservice-application-name
        path = "api/invoices", //pre-path for service endpoints
        //url = "${invoice.service.url}",
        configuration = FeignConfiguration.class)
public interface InvoiceServiceRequest {

    @GetMapping///invoices
    List <Object> getAllInvoices();

//    @GetMapping("{id}")///invoices/{id}
//    List<Object> getInvoiceById(@PathVariable Long id);

    @GetMapping("/reference/{reference}")///invoices/reference/{reference}
   String getInvoiceByReference(@PathVariable(value = "reference") String reference);

    @PostMapping//invoices
    Object newInvoice(@RequestBody Object newInvoice);

    @GetMapping("{userId}")
    List<Object> getAllInvoicesOfUser(@PathVariable(value = "userId") Long userId);
}
