package com.lolucode.financeInvoice.service;

import com.lolucode.financeInvoice.exception.*;
import com.lolucode.financeInvoice.model.*;
import com.lolucode.financeInvoice.repository.*;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AccountServiceImpl implements AccountService{


    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createNewAccount(Account account) {
        if(account.getUserId()==null || account.getUserId()==0){
            throw new AccountNotValidException();
        }
        if(accountRepository.findAccountByUserId(account.getUserId()).isPresent() ){
            throw new InvalidUserException("An account exists with user id" + account.getUserId());
         }
        while (true){
            account.populateStudentId();
            String studentID = account.getStudentId();
            Account account1 = accountRepository.findAccountByStudentId(studentID);
            if(account1==null){
                break;
            }
        }
        Account savedAccount;
        try {
            savedAccount = accountRepository.save(account);
        } catch (DataIntegrityViolationException exception) {
            throw new AccountNotValidException("An account already exists for student ID" + account.getStudentId() + ".");
        }
        createLibraryAccount(savedAccount);
        return savedAccount;
    }

    @Override
    public Account createAccountIfNotExist(Account account) {
        if(account.getUserId()==null || account.getUserId()==0){
            throw new AccountNotValidException();
        }
        Optional<Account> account1 = accountRepository.findAccountByUserId(account.getUserId());
        if(account1.isPresent()){
            return account1.get();
        }

        while (true){
            account.populateStudentId();
            String studentID = account.getStudentId();
            Account account2 = accountRepository.findAccountByStudentId(studentID);
            if(account2==null){
                break;
            }
        }
        Account savedAccount;
        try {
            savedAccount = accountRepository.save(account);
        } catch (DataIntegrityViolationException exception) {
            throw new AccountNotValidException("An account already exists for student ID" + account.getStudentId() + ".");
        }
        return savedAccount;
    }

    @Override
    public Account createLibraryAccount(Account account) {
        System.out.println(account.getStudentId());
        Map<String,String> student  = new HashMap<>();
        student.put("studentId", account.getStudentId());

        HttpEntity<Map<String,String>> entity1 = new HttpEntity<>(student);
        String libraryAccount = (new RestTemplate()).exchange("http://localhost:80/api/register", HttpMethod.POST,entity1, String.class).getBody();

        System.out.println(libraryAccount);
        return account;
    }

    @Override
    public Account findAccountByStudentId (String studentId) {
        Account studentAccount = accountRepository.findAccountByStudentId(studentId);
        if (studentAccount == null) {
            throw new InvalidAccountException();
        }
        return calculateOutstandingBalance(studentAccount);
        //return accountRepository.findAccountByStudentId(studentId);
    }


    public Account calculateOutstandingBalance(Account account) {
        if (account != null) {
            List<Invoice> invoices = invoiceRepository.findInvoiceByUserIdAndStatus(account.getUserId(), Status.OUTSTANDING);

            if (invoices!= null && !invoices.isEmpty()) {
                account.setHasOutstandingBalance(invoices.stream()
                        .anyMatch(invoice -> invoice.getStatus().equals(Status.OUTSTANDING)));
            }
        }
        return account;
    }

    @Override
    public Account getAccountByUser(Long userId) {

        Optional<Account> userAccount = accountRepository.findAccountByUserId(userId);
        if (userAccount.isEmpty()) {
            throw new InvalidAccountException();
        }
        return calculateOutstandingBalance(userAccount.get());

    }
}
