package com.br.edercnj.credentials.core.domain.entities;

import com.br.edercnj.credentials.core.domain.exception.InvalidEmailException;
import com.br.edercnj.credentials.core.domain.exception.InvalidPasswordExpcetion;

import java.time.Instant;

public class AccountCredential {

    private final String accountId;
    private Password pawssword;
    private final Username username;
    private final Instant createdAt;
    private Instant updatedAt;

    public AccountCredential(String accountId, String pawssword, String username) throws InvalidPasswordExpcetion, InvalidEmailException {
        this.accountId = accountId;
        this.pawssword = new Password(pawssword);
        this.username = new Username(username);
        this.createdAt = Instant.now();
    }

    public void changePassword(String password) throws InvalidPasswordExpcetion {
        this.pawssword = new Password(password);
        this.updatedAt = Instant.now();
    }

    public String getAccountId() {return this.accountId;}

    public Password getPawssword() {return pawssword;}

    public Username getUsername() {return username;}

    public Instant getCreatedAt() {return createdAt;}

    public Instant getUpdatedAt() {return updatedAt;}
}


