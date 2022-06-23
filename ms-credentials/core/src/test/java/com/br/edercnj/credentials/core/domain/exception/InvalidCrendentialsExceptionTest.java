package com.br.edercnj.credentials.core.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class InvalidCrendentialsExceptionTest {

    @Test
    void given_throwInvalidCrendentialsException_when_callDefaultConstructor_then_exceptionMessageIsDefaultMessage() {
        InvalidCrendentialsException expcetion = new InvalidCrendentialsException();
        Assertions.assertEquals("Username or password invalid", expcetion.getMessage());
    }

}