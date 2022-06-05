package com.br.edercnj.credentials.core.domain.entities;

import com.br.edercnj.credentials.core.domain.exception.InvalidEmailException;
import com.br.edercnj.credentials.core.domain.validation.EmailValidation;

public class Username {
    public final String value;

    public Username(String username) throws InvalidEmailException {
        if (!EmailValidation.isValid(username)) {
            throw new InvalidEmailException();
        }
        this.value = username;
    }

    public String getValue() {return value;}
}