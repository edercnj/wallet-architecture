package br.com.edercnj.useraccount.core.domain.cryptography;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class EncryptionArgon2 implements Encryption {
    @Override
    public String encrypt(String text)  {
        Argon2 argon2 = Argon2Factory.create();
        try {
            String encryptedPassword = argon2.hash(22, 65536, 1, text.toCharArray());
            return null;
        } finally {
            argon2.wipeArray(text.toCharArray());
        }
    }
}
