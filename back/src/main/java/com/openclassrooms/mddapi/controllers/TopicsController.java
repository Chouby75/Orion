package com.openclassrooms.mddapi.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.mddapi.data.services.TopicService;
import com.openclassrooms.mddapi.dto.TopicsDto;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/topics")
@RestController
public class TopicsController {

    private final TopicService topicService;

    public TopicsController(TopicService topicService){
        this.topicService = topicService;
    }

    @GetMapping()
    public List<TopicsDto> getMethodName() {
        return topicService.getTopics();
    }
    
}
