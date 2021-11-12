package com.br.edercnj.walletuser.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InsufficientFundsExceptionTest {

    @Test
    void insufficientFundsException_getMessage_should_be_return_insufficient_founds() {
        Assertions.assertEquals("Insufficient Funds", new InsufficientFundsException().getMessage());
    }

}