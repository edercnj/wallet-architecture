package com.br.edercnj.credentials.core.domain.exception;

public class FiledToCreateUserException extends Exception {
    public FiledToCreateUserException(Exception ex) {
        super(ex.getMessage());
    }
}
