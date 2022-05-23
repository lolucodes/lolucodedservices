package com.lolucode.financeInvoice.exception;

import com.ctc.wstx.shaded.msv_core.grammar.OtherExp;

public class InvalidAccountException extends RuntimeException {

    public InvalidAccountException() {
        super("Not a valid account.");
    }
    public InvalidAccountException(String message) {
        super(message);
    }

}
