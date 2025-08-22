package com.openclassrooms.mddapi.data.services;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.data.entity.UserEntity;
import com.openclassrooms.mddapi.dto.tokenDto;
import com.openclassrooms.mddapi.dto.UserInputDto;
import com.openclassrooms.mddapi.data.repo.UserRepo;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    private final UserRepo userRepository; // Assume UserRepository is defined elsewhere
    private final PasswordEncoder passwordEncoder; // Assume PasswordEncoder is defined elsewhere

    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public tokenDto registerUser(UserInputDto user) {
        // Check if the user already exists
        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return new tokenDto("Username already exists");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

        if (newUser.getId() != null) {
            return new tokenDto("User registered successfully");
        } else {
            return new tokenDto("Error registering user");
        }
    }

    public tokenDto loginUser(UserInputDto user) {

        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        if (!existingUser.isPresent()) {
            existingUser = userRepository.findByEmail(user.getUsername());
        }
        if (!existingUser.isPresent()
                || !passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return new tokenDto("Invalid username or password");
        }

        return new tokenDto("Login successful");
    }
}
