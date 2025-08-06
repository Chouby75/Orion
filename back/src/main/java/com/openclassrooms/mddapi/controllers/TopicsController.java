package com.openclassrooms.mddapi.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.mddapi.data.services.TopicService;
import com.openclassrooms.mddapi.dto.MessageToReturn;
import com.openclassrooms.mddapi.dto.TopicsDto;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/topics")
@RestController
public class TopicsController {

    private final TopicService topicService;

    public TopicsController(TopicService topicService){
        this.topicService = topicService;
    }

    @GetMapping()
    public Set<TopicsDto> getMethodName() {
        return topicService.getTopics();
    }

    @GetMapping("/choice")
    public MessageToReturn getAllChoice() {

        return new MessageToReturn("oui");
    }

    //route pour les topics abonnés

    //route pour s'abonner à un topic

    //route pour se désabonner d'un topic
    
}
