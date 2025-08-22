package com.openclassrooms.mddapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.openclassrooms.mddapi.data.entity.UserEntity;
import com.openclassrooms.mddapi.data.repo.UserRepo;
import com.openclassrooms.mddapi.data.services.TopicService;
import com.openclassrooms.mddapi.dto.MessageToReturn;
import com.openclassrooms.mddapi.dto.TopicsDto;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/topics")
@RestController
public class TopicsController {

    private final TopicService topicService;

    private final UserRepo userRepo;

    public TopicsController(TopicService topicService, UserRepo userRepo) {
        this.topicService = topicService;
        this.userRepo = userRepo;
    }

    @GetMapping()
    public Set<TopicsDto> getMethodName(Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return null;
        }
        UserEntity user = userRepo.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return topicService.getTopics(user);
    }

    @PutMapping("/subscribe")
    public MessageToReturn subscribeToTopic(@RequestParam Long topicId, Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return new MessageToReturn("User not authenticated or author not specified");
        }
        Long userId = userRepo.findByUsername(username).get().getId();
        Boolean result = topicService.subscribeToTopic(topicId, userId);
        MessageToReturn response = new MessageToReturn(null);
        if (result) {
            response.setMessage("Subscription successful");
        } else {
            response.setMessage("Subscription failed");
        }
        return response;
    }

    @PutMapping("/unsubscribe")
    public MessageToReturn unsubscribeFromTopic(@RequestParam Long topicId, Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return new MessageToReturn("User not authenticated or author not specified");
        }
        Long userId = userRepo.findByUsername(username).get().getId();
        Boolean result = topicService.unsubscribeFromTopic(topicId, userId);
        MessageToReturn response = new MessageToReturn(null);
        if (result) {
            response.setMessage("Unsubscription successful");
        } else {
            response.setMessage("Unsubscription failed");
        }
        return response;
    }

}
