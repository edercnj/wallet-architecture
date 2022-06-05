package com.br.edercnj.credentials.core.ports.outbound;

import com.br.edercnj.credentials.core.domain.entities.AccountCredential;

import javax.security.auth.login.AccountNotFoundException;

public interface AccountCredentialRepositoryPort {

    void persistAccountCredential(AccountCredential accountCredential);
    AccountCredential findAccountByUsername(String usermame) throws AccountNotFoundException;
}
