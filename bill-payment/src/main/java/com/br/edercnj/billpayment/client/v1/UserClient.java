package com.br.edercnj.billpayment.client.v1;

import com.br.edercnj.billpayment.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user", url = "${feign.client.wallet-user.find-user.base-url}")
public interface UserClient {
    @GetMapping(value = "/users")
    UserDto getUserByUsername(@RequestParam("username") String username);
}
