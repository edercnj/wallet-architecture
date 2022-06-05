package br.com.edercnj.useraccount.core.domain.entities;

import br.com.edercnj.useraccount.core.domain.exception.AesEncryptException;
import br.com.edercnj.useraccount.core.domain.exception.InvalidPasswordExpcetion;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;

public interface User {

    String getId();

    void changePassword(String password) throws InvalidPasswordExpcetion, NoSuchAlgorithmException, AesEncryptException, InvalidKeySpecException;

    String getUsername();

    Password getPawssword();

    Email getEmail();

    String[] getScopes();

    Instant getCreatedAt();

    Instant getUpdatedAt();
}
