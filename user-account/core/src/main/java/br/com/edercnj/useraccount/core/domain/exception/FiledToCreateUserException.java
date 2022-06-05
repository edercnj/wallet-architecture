package br.com.edercnj.useraccount.core.domain.exception;

public class FiledToCreateUserException extends Exception {
    public FiledToCreateUserException(Exception ex) {
        super(ex.getMessage());
    }
}
