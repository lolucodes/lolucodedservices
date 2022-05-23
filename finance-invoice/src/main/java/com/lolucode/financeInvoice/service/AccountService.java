package com.lolucode.financeInvoice.service;

import com.lolucode.financeInvoice.model.Account;

import java.util.List;

public interface AccountService {

    Account createNewAccount(Account account);
    Account findAccountByStudentId(String studentId);

    Account getAccountByUser(Long userId);

    public Account calculateOutstandingBalance(Account account);

    public Account createAccountIfNotExist(Account account);

    public Account createLibraryAccount(Account account);
}
