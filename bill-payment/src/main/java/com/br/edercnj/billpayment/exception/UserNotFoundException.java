package com.br.edercnj.billpayment.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found.");
    }
}
