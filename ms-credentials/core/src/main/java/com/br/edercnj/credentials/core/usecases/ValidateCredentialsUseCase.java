package com.br.edercnj.credentials.core.usecases;

import com.br.edercnj.credentials.core.domain.entities.AccountCredential;
import com.br.edercnj.credentials.core.domain.entities.Password;
import com.br.edercnj.credentials.core.domain.exception.InvalidCrendentialsException;
import com.br.edercnj.credentials.core.ports.outbound.AccountCredentialRepositoryPort;
import com.br.edercnj.credentials.core.ports.outbound.PasswordRepositoryPort;

import javax.security.auth.login.AccountNotFoundException;

public class ValidateCredentialsUseCase {

    AccountCredentialRepositoryPort accountCredentialPepository;
    PasswordRepositoryPort passwordRepository;

    public boolean validateCredentials(String username, String password) throws InvalidCrendentialsException, AccountNotFoundException {
        AccountCredential accountCredential = accountCredentialPepository.findAccountByUsername(username);
        if (accountCredential == null) {
            throw new InvalidCrendentialsException();
        } else {
            Password pass = passwordRepository.findPasswordById(accountCredential.getPawssword().getPasswordId());
            if (!pass.passwordEquals(password)) {
                throw new InvalidCrendentialsException();
            }
        }
        return true;
    }
}
