package com.br.edercnj.credentials.core.domain.entities;

import com.br.edercnj.credentials.core.domain.exception.InvalidPasswordExpcetion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordTest {

    private Password password;

    @BeforeEach
    void setUp() throws InvalidPasswordExpcetion {
        password = new Password("Password@1234");
    }

    @Test
    void getPassword() {
        String pass = password.getPassword();
        Assertions.assertNotNull(pass);
    }

    @Test
    void getPasswordWithHashedPasswordInConstructor() throws InvalidPasswordExpcetion {
        Password newPassword = new Password(password.getPassword());
        Assertions.assertEquals(newPassword.getPassword(), password.getPassword());
    }


    @Test
    void equalsTest() {
        String pass = "Password@1234";
        Assertions.assertTrue(password.passwordEquals(pass));
    }

    @Test
    void equalsTestFalse() {
        String pass = "Password@123";
        Assertions.assertFalse(password.passwordEquals(pass));
    }
}