package com.br.edercnj.walletuser.adapters.outbound.persistence.mongo;

import com.br.edercnj.walletuser.adapters.outbound.persistence.mongo.entities.UserMongoEntity;
import com.br.edercnj.walletuser.application.domain.entities.User;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepositoryPort;
import com.br.edercnj.walletuser.application.domain.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMongoRepository implements UserRepositoryPort {

    private final UserSpringDataMongoRepository userSpringDataMongoRepository;
    private final ModelMapper mapper;

    public UserMongoRepository(UserSpringDataMongoRepository userSpringDataMongoRepository) {
        this.userSpringDataMongoRepository = userSpringDataMongoRepository;
        mapper = new ModelMapper();
    }

    @Override
    public User findByUsername(String username) throws UserNotFoundException {
        Optional<UserMongoEntity> entity = userSpringDataMongoRepository.findByUsername(username);
        if (entity.isPresent()) {
            return mapper.map(entity.get(), User.class);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User findUserById(String userId) throws UserNotFoundException {
        Optional<UserMongoEntity> entity = userSpringDataMongoRepository.findById(userId);
        if (entity.isPresent()) {
            return mapper.map(entity.get(), User.class);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User saveUser(User user) {
        UserMongoEntity entity = mapper.map(user, UserMongoEntity.class);
        return mapper.map(userSpringDataMongoRepository.save(entity), User.class);
    }
}
