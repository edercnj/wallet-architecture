package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.entities.User;

import java.math.BigDecimal;

public class UserMock {

    public static User createUser() {
        return new User("falano@fulano.com.br", "Fulano", new BigDecimal(10));
    }
}
