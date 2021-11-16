package com.br.edercnj.billpayment.client.v1;

import com.br.edercnj.billpayment.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user", url = "http://wallet-gateway:8000/api/v1/user")
public interface UserClient {
    @GetMapping(value = "/find")
    UserDto getUserByUsername(@RequestParam("username") String username);
}
