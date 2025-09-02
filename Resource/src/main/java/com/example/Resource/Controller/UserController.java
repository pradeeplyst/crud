package com.example.Resource.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Resource.Entity.AddressEntity;
import com.example.Resource.Entity.UserEntity;
import com.example.Resource.Repository.UserRepository;

@RestController
@RequestMapping("/api/resources")
public class UserController {

    @Autowired
    private UserRepository userRepository;

        @PostMapping
    public UserEntity createUser(@RequestBody UserEntity user) {
        for (AddressEntity address : user.getAddresses()) {
            address.setUser(user); 
        }
        return userRepository.save(user);  
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }
}
