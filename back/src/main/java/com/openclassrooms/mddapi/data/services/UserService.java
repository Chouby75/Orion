package com.openclassrooms.mddapi.data.services;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.data.entity.UserEntity;
import com.openclassrooms.mddapi.dto.MessageToReturn;
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

    public MessageToReturn registerUser(UserInputDto user) {
        // Check if the user already exists
        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return new MessageToReturn("Username already exists");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

        if (newUser.getId() != null) {
            return new MessageToReturn("User registered successfully");
        } else {
            return new MessageToReturn("Error registering user");
        }
    }

    public MessageToReturn loginUser(UserInputDto user) {
        Optional<UserEntity> existingUser = userRepository.findByUsername(user.getUsername());
        if (!existingUser.isPresent() || !passwordEncoder.matches(user.getPassword(), existingUser.get().getPassword())) {
            return new MessageToReturn("Invalid username or password");
        }

        return new MessageToReturn("Login successful");
    }
}
