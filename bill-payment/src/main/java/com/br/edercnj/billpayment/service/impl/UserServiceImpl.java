package com.br.edercnj.billpayment.service.impl;

import com.br.edercnj.billpayment.client.v1.UserClient;
import com.br.edercnj.billpayment.model.dto.UserDto;
import com.br.edercnj.billpayment.model.entity.User;
import com.br.edercnj.billpayment.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserClient userClient;
    private final ModelMapper mapper;

    public UserServiceImpl(UserClient userClient) {
        this.userClient = userClient;
        mapper = new ModelMapper();
    }

    @Override
    public User findUserByUsername(String username) {
        UserDto userDto = userClient.getUserByUsername(username);
        return mapper.map(userDto, User.class);
    }
}
