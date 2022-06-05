package br.com.edercnj.useraccount.core.domain.entities;

import br.com.edercnj.useraccount.core.domain.cryptography.Encryption;
import br.com.edercnj.useraccount.core.domain.cryptography.EncryptionArgon2;
import br.com.edercnj.useraccount.core.domain.exception.InvalidPasswordExpcetion;
import br.com.edercnj.useraccount.core.domain.validation.PasswordValidator;

public class Password {

    private final String encriptedPassword;

    public Password(String openPassword) throws InvalidPasswordExpcetion {
        PasswordValidator.validate(openPassword);
        Encryption encryption = new EncryptionArgon2();
        this.encriptedPassword = encryption.encrypt(openPassword);
    }
    
    public String getEncriptedPassword() {return this.encriptedPassword;}

}
