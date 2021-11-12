package com.br.edercnj.walletuser.exception;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException() {
        super("Insufficient Funds");
    }
}
