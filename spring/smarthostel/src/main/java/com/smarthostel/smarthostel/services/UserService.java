package com.smarthostel.smarthostel.services;

import com.smarthostel.smarthostel.entities.User;
import com.smarthostel.smarthostel.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }
}
