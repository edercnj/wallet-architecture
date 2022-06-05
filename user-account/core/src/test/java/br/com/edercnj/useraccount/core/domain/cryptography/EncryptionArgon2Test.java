package br.com.edercnj.useraccount.core.domain.cryptography;

import br.com.edercnj.useraccount.core.domain.exception.AesEncryptException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EncryptionArgon2Test {

    private Encryption encryption;

    @BeforeEach
    void setUp() {
        encryption = new EncryptionArgon2();
    }

    @Test
    void argo2EncripText() throws AesEncryptException {
        String password = "test";
        String encriptedPassword = encryption.encrypt(password);
        Assertions.assertNotNull(encriptedPassword);
    }
}