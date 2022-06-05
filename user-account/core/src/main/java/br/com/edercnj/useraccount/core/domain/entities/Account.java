package br.com.edercnj.useraccount.core.domain.entities;

import br.com.edercnj.useraccount.core.domain.exception.AesEncryptException;
import br.com.edercnj.useraccount.core.domain.exception.InvalidEmailException;
import br.com.edercnj.useraccount.core.domain.exception.InvalidPasswordExpcetion;

import java.time.Instant;
import java.util.UUID;

public class Account implements User {
    private final String id;
    private final String username;
    private Password pawssword;
    private Email email;
    private final Instant createdAt;
    private Instant updatedAt;
    private final String[] scopes;

    public Account(String username, String pawssword, String email, String[] scopes) throws InvalidPasswordExpcetion, InvalidEmailException {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.pawssword = new Password(pawssword);
        this.email = new Email(email);
        this.scopes = scopes;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public void changePassword(String password) throws InvalidPasswordExpcetion, AesEncryptException {
        this.pawssword = new Password(password);
        this.updatedAt = Instant.now();
    }

    public void changeEmail(String newEmail) throws InvalidEmailException {
        this.email = new Email(newEmail);
        this.updatedAt = Instant.now();
    }

    @Override
    public String getId() {return this.id;}

    @Override
    public String getUsername() {return username;}

    @Override
    public Password getPawssword() {return pawssword;}

    @Override
    public Email getEmail() {return email;}

    @Override
    public String[] getScopes() {return scopes;}

    @Override
    public Instant getCreatedAt() {return createdAt;}

    @Override
    public Instant getUpdatedAt() {return updatedAt;}
}


