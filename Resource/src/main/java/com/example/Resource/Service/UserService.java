package com.example.Resource.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Recover;
import org.springframework.stereotype.Service;

import com.example.Resource.Entity.UserEntity;
import com.example.Resource.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private int attempt = 1;

    @Retryable(
        value = { RuntimeException.class },
        maxAttempts = 3,
        backoff = @Backoff(delay = 2000)
    )
    public UserEntity saveUser(UserEntity user) {
        System.out.println("Attempt #" + attempt);
        if (attempt < 3) {
            attempt++;
            throw new RuntimeException("Simulated failure");
        }
        return userRepository.save(user);
    }

    @Recover
    public UserEntity recover(RuntimeException e, UserEntity user) {
        System.out.println("Recover called: " + e.getMessage());
        return null;
    }
}
