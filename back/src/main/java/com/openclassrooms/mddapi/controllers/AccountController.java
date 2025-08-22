package com.openclassrooms.mddapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.openclassrooms.mddapi.data.services.AccountService;
import com.openclassrooms.mddapi.dto.AccountDetailDto;
import com.openclassrooms.mddapi.dto.AccountUpdateDto;
import com.openclassrooms.mddapi.dto.MessageToReturn;

@RequestMapping("/me")
@RestController
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Route to get user details
    @GetMapping()
    public ResponseEntity<?> getAccount(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return new ResponseEntity<>("User is not authenticated", HttpStatus.UNAUTHORIZED);
        }
        AccountDetailDto userDetails = accountService.getUserDetails(authentication);
        if (userDetails == null) {
            return new ResponseEntity<>("User details not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    // Route to update user details
    @PutMapping()
    public ResponseEntity<?> updateAccount(Authentication authentication,
            @RequestBody AccountUpdateDto accountUpdateDto) {
        Boolean result = accountService.updateUserDetails(authentication, accountUpdateDto);
        MessageToReturn message = new MessageToReturn(null);
        if (result) {
            message.setMessage("update success!");
            return new ResponseEntity<MessageToReturn>(message, HttpStatus.OK);
        } else {
            message.setMessage("update failed!");
            return new ResponseEntity<MessageToReturn>(message, HttpStatus.BAD_REQUEST);
        }
    }

}
