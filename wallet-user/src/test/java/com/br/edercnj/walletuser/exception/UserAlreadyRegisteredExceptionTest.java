package com.br.edercnj.walletuser.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserAlreadyRegisteredExceptionTest {

    @Test
    void userAlreadyRegisteredException_getMessage_should_be_return_default_message() {
        Assertions.assertEquals("User already registered", new UserAlreadyRegisteredException().getMessage());
    }
}