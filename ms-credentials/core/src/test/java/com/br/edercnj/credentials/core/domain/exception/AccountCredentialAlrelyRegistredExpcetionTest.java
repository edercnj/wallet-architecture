package com.br.edercnj.credentials.core.domain.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AccountCredentialAlrelyRegistredExpcetionTest {

    @Test
    void given_throwAccountCredentialAlrelyRegistredExpcetion_when_callDefaultConstructor_then_exceptionMessageIsDefaultMessage() {
        AccountCredentialAlrelyRegistredExpcetion expcetion = new AccountCredentialAlrelyRegistredExpcetion();
        Assertions.assertEquals("Credentials alrely registred", expcetion.getMessage());
    }
}