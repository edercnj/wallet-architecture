package com.br.edercnj.walletuser.application.domain.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found.");
    }
}
