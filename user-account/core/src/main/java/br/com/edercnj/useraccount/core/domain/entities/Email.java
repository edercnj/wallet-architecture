package br.com.edercnj.useraccount.core.domain.entities;

import br.com.edercnj.useraccount.core.domain.exception.InvalidEmailException;
import br.com.edercnj.useraccount.core.domain.validation.EmailValidation;

public class Email {
    public final String emailAdress;

    public Email(String email) throws InvalidEmailException {

        if (!EmailValidation.isValid(email)) {
            throw new InvalidEmailException();
        }
        this.emailAdress = email;
    }

    public String getEmailAdress() {return emailAdress;}
}