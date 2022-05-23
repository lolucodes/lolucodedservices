package com.lolucode.financeInvoice.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long userId) {
        super("Could not find account " + userId);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
