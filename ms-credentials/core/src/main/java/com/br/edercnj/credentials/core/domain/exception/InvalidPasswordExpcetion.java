package com.br.edercnj.credentials.core.domain.exception;

public class InvalidPasswordExpcetion extends Exception {
    public InvalidPasswordExpcetion() {
        super("The password does not meet the minimum security criteria which are: letters, numbers, at least one uppercase letter, one lowercase letter, one special character, minimum of 8 characters and maximum of 50 characters.");
    }
}
