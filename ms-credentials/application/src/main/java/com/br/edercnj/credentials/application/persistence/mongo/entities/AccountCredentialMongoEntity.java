package com.br.edercnj.credentials.application.persistence.mongo.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.Instant;

@Document
@Data
public class AccountCredentialMongoEntity {

    @Id
    @Indexed
    private String accountId;
    private String password;
    @Indexed
    private String username;
    private Instant createdAt;
    private Instant updatedAt;
}
