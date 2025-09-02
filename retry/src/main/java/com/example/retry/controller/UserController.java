package com.example.retry.controller;

import com.example.retry.entity.UserEntity;
import com.example.retry.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
	Logger logger  = LoggerFactory.getLogger(UserController.class);
	logger.info(" debug level");

    @Autowired
    private UserService userService;

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        return userService.saveUser(user);
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}
}
