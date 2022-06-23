package com.br.edercnj.credentials.application.web.api;

import com.br.edercnj.credentials.application.model.AccountCredentialDto;
import com.br.edercnj.credentials.application.persistence.mongo.AccountCredentialMongoRepository;
import com.br.edercnj.credentials.core.domain.entities.AccountCredential;
import com.br.edercnj.credentials.core.ports.inbound.AccountCredentialWebPort;
import com.br.edercnj.credentials.core.usecases.CreateAccountCredentialUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class AccountCredentialWeb implements AccountCredentialWebPort {

    private final CreateAccountCredentialUseCase createAccountCredentialUseCase;
    private final AccountCredentialMongoRepository accountCredentialMongoRepository;

    public AccountCredentialWeb(AccountCredentialMongoRepository accountCredentialMongoRepository) {
        this.accountCredentialMongoRepository = accountCredentialMongoRepository;
        this.createAccountCredentialUseCase = new CreateAccountCredentialUseCase(accountCredentialMongoRepository);
    }

    @PostMapping("/account_credentials")
    public ResponseEntity<AccountCredentialDto> createAccountCredential(AccountCredentialDto accountCredentialDto) {

        return null;
    }


    @Override
    public AccountCredential createAccountCredential(String email, String pawssword) {
        return null;
    }

    @Override
    public void changePassword(String accountId, String newPassword) {

    }

    @Override
    public boolean validateCredentials(String email, String pawssword) {
        return false;
    }
}
