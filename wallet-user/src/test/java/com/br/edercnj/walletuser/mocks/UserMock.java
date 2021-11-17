package com.br.edercnj.walletuser.mocks;

import com.br.edercnj.walletuser.model.entities.User;

import java.math.BigDecimal;

public class UserMock {

    public static User createUser() {
        User user = new User("falano@fulano.com.br", "Fulano", new BigDecimal(10));
        user.setId("6194205940ddae1fa2d4388e");
        return user;
    }
}
