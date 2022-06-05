package com.br.edercnj.credentials.core.usecases;

import com.br.edercnj.credentials.core.domain.entities.AccountCredential;
import com.br.edercnj.credentials.core.domain.entities.ForgotPassord;
import com.br.edercnj.credentials.core.ports.outbound.AccountCredentialRepositoryPort;
import com.br.edercnj.credentials.core.ports.outbound.EmailMessagePort;

import javax.security.auth.login.AccountNotFoundException;

public class ForgotPasswordUseCase {

    private final AccountCredentialRepositoryPort accountCredentialRepository;
    private final EmailMessagePort forgotPasswordMessage;

    public ForgotPasswordUseCase(AccountCredentialRepositoryPort accountCredentialRepository, EmailMessagePort forgotPasswordMessage) {
        this.accountCredentialRepository = accountCredentialRepository;
        this.forgotPasswordMessage = forgotPasswordMessage;
    }

    public void forgotPassword(String email) throws AccountNotFoundException {
        AccountCredential accountCredential = accountCredentialRepository.findAccountByUsername(email);
        ForgotPassord forgotPassord = new ForgotPassord(accountCredential);
        forgotPasswordMessage.sendForgotPasswordMessage(forgotPassord);
    }
}
