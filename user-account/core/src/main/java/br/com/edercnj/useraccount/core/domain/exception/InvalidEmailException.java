package br.com.edercnj.useraccount.core.domain.exception;

public class InvalidEmailException extends Exception {

    public InvalidEmailException() {
        super("Email not valid");
    }
}
