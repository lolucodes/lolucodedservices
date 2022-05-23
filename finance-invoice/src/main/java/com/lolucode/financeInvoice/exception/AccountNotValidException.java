package com.lolucode.financeInvoice.exception;

public class AccountNotValidException extends RuntimeException {

    public AccountNotValidException() {
        super("Not a valid account.");
    }
    public AccountNotValidException(String message) {
        super(message);
    }
}
