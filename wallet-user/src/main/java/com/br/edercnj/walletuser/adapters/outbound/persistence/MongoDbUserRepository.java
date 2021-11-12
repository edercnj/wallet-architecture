package com.br.edercnj.walletuser.adapters.outbound.persistence;

import com.br.edercnj.walletuser.adapters.outbound.persistence.entities.UserEntity;
import com.br.edercnj.walletuser.application.domain.entites.User;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MongoDbUserRepository implements UserRepository {

    private final SpringDataMongoDbUserRepository userRepository;

    private final ModelMapper modelMapper;

    public MongoDbUserRepository(SpringDataMongoDbUserRepository userRepository) {
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userRepository.save(modelMapper.map(user, UserEntity.class));
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public User updateWalletBalance(User user) {
        UserEntity userEntity = userRepository.save(modelMapper.map(user, UserEntity.class));
        return modelMapper.map(userEntity, User.class);
    }
}
