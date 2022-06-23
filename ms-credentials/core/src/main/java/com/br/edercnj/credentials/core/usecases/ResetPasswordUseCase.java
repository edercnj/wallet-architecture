package com.br.edercnj.credentials.core.usecases;

import com.br.edercnj.credentials.core.domain.entities.AccountCredential;
import com.br.edercnj.credentials.core.domain.entities.ForgotPassord;
import com.br.edercnj.credentials.core.domain.exception.InvalidCrendentialsException;
import com.br.edercnj.credentials.core.domain.exception.InvalidPasswordExpcetion;
import com.br.edercnj.credentials.core.domain.exception.InvalidRoveryCodeException;
import com.br.edercnj.credentials.core.domain.exception.RecoveryCodeExpiredException;
import com.br.edercnj.credentials.core.ports.outbound.AccountCredentialRepositoryPort;
import com.br.edercnj.credentials.core.ports.outbound.ForgotPasswordRepositoryPort;
import com.br.edercnj.credentials.core.ports.outbound.PasswordRepositoryPort;

import javax.security.auth.login.AccountNotFoundException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class ResetPasswordUseCase {

    private final PasswordRepositoryPort passwordRepository;
    private final AccountCredentialRepositoryPort accountCredentialRepository;
    private final ForgotPasswordRepositoryPort forgotPasswordRepository;

    public ResetPasswordUseCase(PasswordRepositoryPort passwordRepository, AccountCredentialRepositoryPort accountCredentialRepository, ForgotPasswordRepositoryPort forgotPasswordRepository) {
        this.passwordRepository = passwordRepository;
        this.accountCredentialRepository = accountCredentialRepository;
        this.forgotPasswordRepository = forgotPasswordRepository;
    }

    public void resetPassword(String username, int recoveryPasswordCode, String newPassword) throws InvalidCrendentialsException, InvalidRoveryCodeException, RecoveryCodeExpiredException, InvalidPasswordExpcetion, AccountNotFoundException {
        AccountCredential accountCredential = accountCredentialRepository.findAccountByUsername(username);
        if (accountCredential == null) {
            throw new InvalidCrendentialsException();
        }
        ForgotPassord forgotPassord = forgotPasswordRepository.findByRecoveryCode(recoveryPasswordCode);
        if (forgotPassord == null) {
            throw new InvalidRoveryCodeException();
        } else if (forgotPassord.getCreatedAt().plus(forgotPassord.getTimeoutRecovery(), ChronoUnit.MILLIS).isAfter(Instant.now())) {
            throw new RecoveryCodeExpiredException();
        }
        accountCredential.changePassword(newPassword);
        passwordRepository.persistPassword(accountCredential.getPawssword());
        accountCredentialRepository.persistAccountCredential(accountCredential);
    }

}
