package com.lolucode.financeInvoice.controller;

import com.lolucode.financeInvoice.model.Account;
import com.lolucode.financeInvoice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> createNewAccount(@RequestBody @NotEmpty Account newAccount) {
        return new ResponseEntity<>(accountService.createNewAccount(newAccount), HttpStatus.CREATED);
    }

    @GetMapping("student/{studentId}")//api/accounts/{studentId}
    public ResponseEntity<?> findAccountByStudentId(@PathVariable String studentId) {
        return ResponseEntity.ok(accountService.findAccountByStudentId(studentId));
    }

    @GetMapping("{userId}")//api/account/{userId}
    public ResponseEntity<?> getUserAccount(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.getAccountByUser(userId));
    }
}


