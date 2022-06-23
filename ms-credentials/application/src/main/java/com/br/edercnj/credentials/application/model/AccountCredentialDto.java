package com.br.edercnj.credentials.application.model;


import lombok.Data;

import java.time.Instant;

@Data
public class AccountCredentialDto {

    private  String accountId;
    private String pawssword;
    private String username;
    private  Instant createdAt;
    private Instant updatedAt;

}


