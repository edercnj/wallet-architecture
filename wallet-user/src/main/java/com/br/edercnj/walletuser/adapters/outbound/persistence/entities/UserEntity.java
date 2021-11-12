package com.br.edercnj.walletuser.adapters.outbound.persistence.entities;

import com.br.edercnj.walletuser.application.domain.entites.Wallet;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String name;
    private Wallet wallet;
}
