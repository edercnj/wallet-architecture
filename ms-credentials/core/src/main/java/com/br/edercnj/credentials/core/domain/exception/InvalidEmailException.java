package com.br.edercnj.credentials.core.domain.exception;

public class InvalidEmailException extends Exception {

    public InvalidEmailException() {
        super("Username must be a valid e-mail");
    }
}
