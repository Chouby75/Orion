package com.openclassrooms.mddapi.data.services;

import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.data.entity.UserEntity;
import com.openclassrooms.mddapi.data.repo.UserRepo;

import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;

import com.openclassrooms.mddapi.dto.AccountDetailDto;
import com.openclassrooms.mddapi.dto.AccountUpdateDto;
import com.openclassrooms.mddapi.dto.TopicsDto;

@Service
public class AccountService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    
    public AccountService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    // Example method to get user details
    public AccountDetailDto getUserDetails(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }
        // 2. Récupère le principal et caste-le en Jwt
        Jwt jwt = (Jwt) authentication.getPrincipal();
        // 3. Extrait l'identifiant de l'utilisateur (le 'subject' du token)
        String username = jwt.getSubject();

        // 4. Utilise le repo pour trouver l'entité utilisateur correspondante
        UserEntity userEntity = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        AccountDetailDto userDetails = new AccountDetailDto();
        userDetails.setId(userEntity.getId());
        userDetails.setUsername(userEntity.getUsername());
        userDetails.setEmail(userEntity.getEmail());
        userDetails.setTopics(userEntity.getSubscriptions().stream()
            .map(topic -> new TopicsDto(topic.getId(), topic.getName(), topic.getDescription(), true))
            .collect(Collectors.toSet()));

        return userDetails;
    }
    
    // Example method to update user details
    public Boolean updateUserDetails(Authentication authentication, AccountUpdateDto userDetails) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalArgumentException("User is not authenticated");
        }

        // 2. Récupère le principal et caste-le en Jwt
        Jwt jwt = (Jwt) authentication.getPrincipal();

        // 3. Extrait l'identifiant de l'utilisateur (le 'subject' du token)
        String userEmail = jwt.getSubject();

        // 4. Utilise le repo pour trouver l'entité utilisateur correspondante
        UserEntity userEntity = userRepo.findByUsername(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + userEmail));

        if (!userDetails.getUsername().equals(userEntity.getUsername())) {
            if (userRepo.findByUsername(userDetails.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Username already exists");
            }
            userEntity.setUsername(userDetails.getUsername());
        }
        if (!userDetails.getEmail().equals(userEntity.getEmail())) {
            if (userRepo.findByEmail(userDetails.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already exists");
            }
            userEntity.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            userEntity.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        UserEntity userSave = userRepo.save(userEntity);
        return userSave != null;
    }
    
}
