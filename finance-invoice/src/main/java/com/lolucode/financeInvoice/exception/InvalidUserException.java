package com.lolucode.financeInvoice.exception;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(Long userId) {
        super("Could not find account " + userId);
    }

    public InvalidUserException(String message){
        super(message);
    }

}
