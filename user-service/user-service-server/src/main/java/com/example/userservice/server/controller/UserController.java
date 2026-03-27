package com.example.userservice.server.controller;

import com.example.userservice.api.entity.UserDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    //@HystrixCommand(fallbackMethod = "getUserFallback")
    public UserDTO getUser(@PathVariable Long id) {
        return new UserDTO(id, "user_" + id, "user" + id + "@example.com");
    }

    public UserDTO getUserFallback(Long id) {
        return new UserDTO(id, "default_user", "default@example.com");
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO(1L, "user_1", "user1@example.com"));
        users.add(new UserDTO(2L, "user_2", "user2@example.com"));
        users.add(new UserDTO(3L, "user_3", "user3@example.com"));
        return users;
    }
}