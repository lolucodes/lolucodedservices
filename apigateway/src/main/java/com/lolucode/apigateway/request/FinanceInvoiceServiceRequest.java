package com.lolucode.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "invoices-service" //Name of finance-invoice-service application
        , path = "api/invoices"
        //,url = "invoices.service.url"
        ,configuration = FeignConfiguration.class
)
public interface FinanceInvoiceServiceRequest {


    @PostMapping// api/invoices
    Object createNewInvoice(@RequestBody Object requestBody);

    @GetMapping("{userId}")//api/invoices/{userId}
    List<Object> getAllInvoicesOfUser(@PathVariable("userId") Long userId);

    @GetMapping("/reference/{reference}")//api/invoice/reference/{reference}
    Object getInvoiceByReference(@PathVariable("reference") String reference);

    @PutMapping("/{reference}/pay")
    Object pay(@PathVariable("reference") String reference);

    @DeleteMapping("/{reference}/cancel")
    Object cancel(@PathVariable("reference") String reference);

    @PutMapping
    Object payInvoice(@RequestBody Object requestBody);
}
