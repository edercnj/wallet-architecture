package br.com.edercnj.useraccount.core.ports.inbounds;

import br.com.edercnj.useraccount.core.domain.entities.Account;

public interface AccountResourcePort {

    Account createAccount(String username, String pawssword, String email, String[] scopes);

    void changeEmail(String email);

    void changePassword(String newPassword);

}
