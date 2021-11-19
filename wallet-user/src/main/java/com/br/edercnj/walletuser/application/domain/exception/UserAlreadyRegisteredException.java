package com.br.edercnj.walletuser.application.domain.exception;

public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException() {
        super("User already registered");
    }
}
