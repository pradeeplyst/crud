package com.example.retry.service;

import com.example.retry.entity.UserEntity;
import com.example.retry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    private int attempt = 1;

    @Retryable(
        value = RuntimeException.class,
        maxAttempts = 3,
        backoff = @Backoff(delay = 2000)
    )
    public UserEntity saveUser(UserEntity user) {
        logger.info("Attempt #" + attempt + " to save user");
        if (attempt++ < 3) {
            throw new RuntimeException("Simulated failure");
        }
        return userRepository.save(user);
    }

    @Scheduled(fixedRate = 1000)
    public void retryJobScheduler() {
        logger.info("Running scheduler task...");

        UserEntity user = new UserEntity();
        user.setName("SchedulerUser");
        user.setEmail("auto@example.com");

        try {
            saveUser(user);
        } catch (Exception e) {
            logger.error("Scheduler failed after retries: " + e.getMessage());
            public void logAllUsers() {
                logger.info("Executing logAllUsers procedure...");
                List<UserEntity> users = userRepository.findAll();
                for (UserEntity user : users) {
                    logger.info("User: " + user.getName() + " | Email: " + user.getEmail());
                }
        }
    }
