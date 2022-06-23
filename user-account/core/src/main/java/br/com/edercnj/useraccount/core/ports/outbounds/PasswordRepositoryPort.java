package br.com.edercnj.useraccount.core.ports.outbounds;

import br.com.edercnj.useraccount.core.domain.entities.Password;

public interface PasswordRepositoryPort {

    Password createPassword(Password password);

    Password updatePassword(Password password);

    Password findPasswordById(String id);

    void deletePassword(Password id);
}
