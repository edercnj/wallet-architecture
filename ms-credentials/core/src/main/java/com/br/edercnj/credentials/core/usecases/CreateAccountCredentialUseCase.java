package com.br.edercnj.credentials.core.usecases;

import com.br.edercnj.credentials.core.domain.entities.AccountCredential;
import com.br.edercnj.credentials.core.domain.exception.AccountCredentialAlrelyRegistredExpcetion;
import com.br.edercnj.credentials.core.domain.exception.InvalidEmailException;
import com.br.edercnj.credentials.core.domain.exception.InvalidPasswordExpcetion;
import com.br.edercnj.credentials.core.ports.outbound.AccountCredentialRepositoryPort;

import javax.security.auth.login.AccountNotFoundException;

public class CreateAccountCredentialUseCase {

    private final AccountCredentialRepositoryPort accountCredentialRepositoryPort;

    public CreateAccountCredentialUseCase(AccountCredentialRepositoryPort accountCredentialRepositoryPort) {
        this.accountCredentialRepositoryPort = accountCredentialRepositoryPort;
    }

    public AccountCredential createCredentials(String accountId, String usermame, String password) throws AccountCredentialAlrelyRegistredExpcetion, InvalidPasswordExpcetion, InvalidEmailException, AccountNotFoundException {
        AccountCredential accountCredential = accountCredentialRepositoryPort.findAccountByUsername(usermame);
        if (accountCredential != null) {
            throw new AccountCredentialAlrelyRegistredExpcetion();
        } else {
            accountCredential = new AccountCredential(accountId, usermame, password);
            accountCredentialRepositoryPort.persistAccountCredential(accountCredential);
            return accountCredential;
        }
    }
}
