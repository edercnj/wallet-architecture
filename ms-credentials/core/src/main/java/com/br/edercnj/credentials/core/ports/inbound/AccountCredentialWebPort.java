package com.br.edercnj.credentials.core.ports.inbound;


import com.br.edercnj.credentials.core.domain.entities.AccountCredential;

public interface AccountCredentialWebPort {

    AccountCredential createAccountCredential(String email, String pawssword);

    void changePassword(String accountId, String newPassword);

    boolean validateCredentials(String email, String pawssword);
}
