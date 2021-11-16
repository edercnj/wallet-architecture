package com.br.edercnj.billpayment.model.entity;

import lombok.Data;

@Data
public class User {
    private String id;
    private String username;
    private String name;
    private Wallet wallet;
}
