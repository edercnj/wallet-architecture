package com.br.edercnj.walletuser.model.entities;

import com.br.edercnj.walletuser.exception.InsufficientFundsException;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document
@Data
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String name;
    private Wallet wallet;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
        this.wallet = new Wallet(new BigDecimal(0));
    }

    public User(String username, String name, BigDecimal initialBalance) {
        this.username = username;
        this.name = name;
        this.wallet = new Wallet(initialBalance);
    }

    public User() {
        this.wallet = new Wallet();
    }

    public BigDecimal depositInWallet(BigDecimal valueToDeposit) {
        return wallet.deposit(valueToDeposit);
    }

    public BigDecimal withdrawInWallet(BigDecimal valueToWithdraw) throws InsufficientFundsException {
        return wallet.withdraw(valueToWithdraw);
    }
}
