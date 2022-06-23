package br.com.edercnj.useraccount.core.ports.outbounds;


import br.com.edercnj.useraccount.core.domain.entities.User;

public interface UserRepositoryPort {

    User createUser(User newUser);

    User findUserById(String id);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void deleteUser(User user);

    User updateUser(User user);
}
