package com.br.edercnj.walletuser.adapters.configuration;

import com.br.edercnj.walletuser.WalletUserApplication;
import com.br.edercnj.walletuser.application.ports.outbound.UserRepository;
import com.br.edercnj.walletuser.application.services.UserServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = WalletUserApplication.class)
public class BeanConfiguration {

    @Bean
    UserServiceImp userServiceImp(UserRepository userRepository) {
        return new UserServiceImp(userRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}