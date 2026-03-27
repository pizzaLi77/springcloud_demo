package com.example.userservice.api.feign.hystrix;

import com.example.userservice.api.feign.UserClient;
import com.example.userservice.api.entity.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public UserDTO getUserById(Long id) {
        return new UserDTO(1L, "操作失败", "00");
    }
}
