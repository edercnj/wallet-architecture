package com.br.edercnj.walletuser.exception;

public class UserAlreadyRegisteredException extends Exception {

    public UserAlreadyRegisteredException() {
        super("User already registered");
    }
}
