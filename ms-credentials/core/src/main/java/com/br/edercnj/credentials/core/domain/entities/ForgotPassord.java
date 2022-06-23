package com.br.edercnj.credentials.core.domain.entities;

import java.time.Instant;
import java.util.random.RandomGenerator;

public class ForgotPassord {
    private final AccountCredential accountCredential;
    private final String recoveryMessage;
    private final int timeoutRecovery = 300000;
    private final int recoveryPasswordCode;
    private final Instant createdAt;

    public ForgotPassord(AccountCredential accountCredential) {
        this.accountCredential = accountCredential;
        recoveryPasswordCode = RandomGenerator.getDefault().nextInt(99999999);
        this.recoveryMessage = String.format("Use code %s to recovery yours password.", recoveryPasswordCode);
        this.createdAt = Instant.now();
    }

    public String getRecoveryMessage() {return recoveryMessage;}

    public int getRecoveryPasswordCode() {return recoveryPasswordCode;}

    public AccountCredential getAccountCredential() {return accountCredential;}

    public int getTimeoutRecovery() {return timeoutRecovery;}

    public Instant getCreatedAt() {return createdAt;}
}
