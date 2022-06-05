package com.br.edercnj.credentials.core.domain.entities;

import com.br.edercnj.credentials.core.domain.exception.InvalidPasswordExpcetion;
import com.br.edercnj.credentials.core.domain.services.HashingArgo2;
import com.br.edercnj.credentials.core.domain.validation.PasswordValidator;

import java.time.Instant;
import java.util.UUID;

public class Password {

    private final String passwordId;
    private final String encriptedPassword;
    private final Instant createdAt;

    public Password(String password) throws InvalidPasswordExpcetion {
        if (HashingArgo2.isHashedPassoword(password)) {
            this.encriptedPassword = password;
        } else {
            PasswordValidator.validate(password);
            this.encriptedPassword = HashingArgo2.hashPassword(password);
        }
        this.passwordId = UUID.randomUUID().toString();
        this.createdAt = Instant.now();
    }

    public Password(String passwordId, String password) throws InvalidPasswordExpcetion {
        if (HashingArgo2.isHashedPassoword(password)) {
            this.encriptedPassword = password;
        } else {
            PasswordValidator.validate(password);
            this.encriptedPassword = HashingArgo2.hashPassword(password);
        }
        this.passwordId = passwordId;
        this.createdAt = Instant.now();
    }

    public boolean passwordEquals(String passwordToCompare) {
        return HashingArgo2.validatePassword(passwordToCompare, encriptedPassword);
    }

    public String getPasswordId() {return passwordId;}

    public String getPassword() {return this.encriptedPassword;}

    public Instant getCreatedAt() {return createdAt;}
}
