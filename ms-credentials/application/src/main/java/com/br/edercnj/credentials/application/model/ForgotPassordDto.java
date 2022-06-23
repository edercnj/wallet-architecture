package com.br.edercnj.credentials.application.model;

import lombok.Data;

import java.time.Instant;

@Data
public class ForgotPassordDto {
    private AccountCredentialDto accountCredentialDto;
    private String recoveryMessage;
    private int recoveryPasswordCode;
    private Instant createdAt;
}
