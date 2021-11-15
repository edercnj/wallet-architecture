package com.br.edercnj.wallettimeline.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found.");
    }
}
