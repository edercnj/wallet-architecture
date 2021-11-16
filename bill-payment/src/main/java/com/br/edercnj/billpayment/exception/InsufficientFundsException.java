package com.br.edercnj.billpayment.exception;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient Funds");
    }
}
