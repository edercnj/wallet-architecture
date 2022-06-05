package com.br.edercnj.credentials.application.persistence.mongo;

import com.br.edercnj.credentials.application.persistence.mongo.entities.AccountCredentialMongoEntity;
import com.br.edercnj.credentials.core.domain.entities.AccountCredential;
import com.br.edercnj.credentials.core.ports.outbound.AccountCredentialRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;


@Service
public class AccountCredentialMongoRepository implements AccountCredentialRepositoryPort {

    private final AccountCredentialSpringDataMongoRepository accountCredentialSpringDataMongoRepository;
    private final ModelMapper modelMapper;

    public AccountCredentialMongoRepository(AccountCredentialSpringDataMongoRepository accountCredentialSpringDataMongoRepository) {
        this.accountCredentialSpringDataMongoRepository = accountCredentialSpringDataMongoRepository;
        this.modelMapper = new ModelMapper();
        modelMapper.typeMap(AccountCredential.class, AccountCredentialMongoEntity.class)
                .addMapping(src -> src.getPawssword().getPassword(), AccountCredentialMongoEntity::setPassword)
                .addMapping(src -> src.getUsername().getValue(), AccountCredentialMongoEntity::setUsername);
    }

    @Override
    public void persistAccountCredential(AccountCredential accountCredential) {
        AccountCredentialMongoEntity entity = modelMapper.map(AccountCredential.class, AccountCredentialMongoEntity.class);
        accountCredentialSpringDataMongoRepository.save(entity);
    }

    @Override
    public AccountCredential findAccountByUsername(String usermame) throws AccountNotFoundException {
        Optional<AccountCredentialMongoEntity> entity = accountCredentialSpringDataMongoRepository.findByUsername(usermame);
        if (entity.isPresent()) {
            return modelMapper.map(entity.get(), AccountCredential.class);
        } else {
            throw new AccountNotFoundException();
        }
    }
}

