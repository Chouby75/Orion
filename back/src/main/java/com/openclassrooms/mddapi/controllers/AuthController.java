package com.openclassrooms.mddapi.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import com.openclassrooms.mddapi.dto.UserInputDto;
import com.openclassrooms.mddapi.dto.MessageToReturn;
import com.openclassrooms.mddapi.data.services.UserService;
import com.openclassrooms.mddapi.security.services.JWTService;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final UserService userService;

    private final JWTService jwtService;

    public AuthController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageToReturn> registerUser(@RequestBody UserInputDto userInput) {
        if (userInput.getUsername() == null || userInput.getEmail() == null || userInput.getPassword() == null) {
            return ResponseEntity.badRequest().body(new MessageToReturn("Username, email, and password are required"));
        }
        MessageToReturn registerUser = userService.registerUser(userInput);
        if (registerUser.getMessage().equals("User registered successfully")) {
            String token = jwtService.generateToken(userInput.getUsername());
            return ResponseEntity.ok(new MessageToReturn(token));
        } else {
            return ResponseEntity.status(400).body(registerUser);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<MessageToReturn> loginUser(@RequestBody UserInputDto userInput) {
        if (userInput.getUsername() == null || userInput.getPassword() == null) {
            return ResponseEntity.badRequest().body(new MessageToReturn("Username and password are required"));
        }
        MessageToReturn loginUser = userService.loginUser(userInput);
        if (loginUser.getMessage().equals("Login successful")) {
            String token = jwtService.generateToken(userInput.getUsername());
            return ResponseEntity.ok(new MessageToReturn(token));
        } else {
            return ResponseEntity.status(400).body(loginUser);
        }
    }
    
}
