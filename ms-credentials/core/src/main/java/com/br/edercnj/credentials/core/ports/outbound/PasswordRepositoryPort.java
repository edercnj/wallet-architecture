package com.br.edercnj.credentials.core.ports.outbound;

import com.br.edercnj.credentials.core.domain.entities.Password;

public interface PasswordRepositoryPort {

    Password findPasswordById(String passwordId);

    void persistPassword(Password password);
}
