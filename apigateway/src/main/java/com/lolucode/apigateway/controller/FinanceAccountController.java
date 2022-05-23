package com.lolucode.apigateway.controller;

import com.lolucode.apigateway.request.FinanceAccountServiceRequest;
import com.lolucode.apigateway.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gateway/accounts")//pre-path
public class FinanceAccountController {

    @Autowired
    private FinanceAccountServiceRequest financeAccountServiceRequest;

    @PostMapping //gateway/accounts
    public ResponseEntity<?> createNewAccount(@RequestBody Object newAccount) {
        return new ResponseEntity<>(financeAccountServiceRequest.createNewAccount(newAccount), HttpStatus.CREATED);
    }

    @GetMapping("/student/{studentId}")//gateway/accounts/student/{studentId}
    public ResponseEntity<?> getAccountByStudentId(@PathVariable String studentId) {
        System.out.println("Came here");
        //financeAccountServiceRequest.getAccountByStudentId(studentId);
        return new ResponseEntity<>(financeAccountServiceRequest.getAccountByStudentId(studentId),HttpStatus.OK);
    }

    @GetMapping //gateway/accounts
    public ResponseEntity<?> getAccountOfAuthorizedUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(financeAccountServiceRequest.getAccountOfUser(userPrincipal.getId()));
    }
}
