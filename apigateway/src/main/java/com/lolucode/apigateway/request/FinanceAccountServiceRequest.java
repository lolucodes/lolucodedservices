package com.lolucode.apigateway.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "invoices-service"//Name of finance-invoice-service application
        , path = "api/accounts"
        //,url = "invoices.service.url"
        , configuration = FeignConfiguration.class
)
public interface FinanceAccountServiceRequest {
    @PostMapping///api/accounts
    Object createNewAccount(@RequestBody Object requestBody);

    @GetMapping("{userId}")//api/accounts/{userId}
    Object getAccountOfUser(@PathVariable("userId") Long userId);

    @GetMapping("/student/{studentId}")///accounts/student/{studentId}
    Object getAccountByStudentId(@PathVariable("studentId") String studentId);
}
