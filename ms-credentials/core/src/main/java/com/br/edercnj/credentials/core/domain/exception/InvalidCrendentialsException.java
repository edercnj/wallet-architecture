package com.br.edercnj.credentials.core.domain.exception;

public class InvalidCrendentialsException extends Exception {

    public InvalidCrendentialsException() {
        super("Username or password invalid");
    }
}
