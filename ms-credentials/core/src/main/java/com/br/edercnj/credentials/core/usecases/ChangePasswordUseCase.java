package com.br.edercnj.credentials.core.usecases;

import com.br.edercnj.credentials.core.domain.entities.AccountCredential;
import com.br.edercnj.credentials.core.domain.exception.InvalidCrendentialsException;
import com.br.edercnj.credentials.core.domain.exception.InvalidPasswordExpcetion;
import com.br.edercnj.credentials.core.ports.outbound.AccountCredentialRepositoryPort;
import com.br.edercnj.credentials.core.ports.outbound.PasswordRepositoryPort;

import javax.security.auth.login.AccountNotFoundException;

public class ChangePasswordUseCase {

    private final AccountCredentialRepositoryPort accountCredentialRepository;
    private final PasswordRepositoryPort passwordRepository;

    public ChangePasswordUseCase(AccountCredentialRepositoryPort accountCredentialRepository, PasswordRepositoryPort passwordRepository) {
        this.accountCredentialRepository = accountCredentialRepository;
        this.passwordRepository = passwordRepository;
    }

    public void changePassword(String username, String password, String newPassword) throws InvalidCrendentialsException, InvalidPasswordExpcetion, AccountNotFoundException {
        AccountCredential accountCredential = accountCredentialRepository.findAccountByUsername(username);
        if (accountCredential == null) {
            throw new InvalidCrendentialsException();
        }
        if (!accountCredential.getPawssword().passwordEquals(password)) {
            throw new InvalidCrendentialsException();
        }
        accountCredential.changePassword(newPassword);
        passwordRepository.persistPassword(accountCredential.getPawssword());
        accountCredentialRepository.persistAccountCredential(accountCredential);
    }
}

