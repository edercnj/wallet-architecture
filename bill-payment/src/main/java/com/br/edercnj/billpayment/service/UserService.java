package com.br.edercnj.billpayment.service;

import com.br.edercnj.billpayment.model.entity.User;

public interface UserService {

     User findUserByUsername(String username);
}
