package br.com.edercnj.useraccount.core.domain.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("DefaultUser not found");
    }
}
