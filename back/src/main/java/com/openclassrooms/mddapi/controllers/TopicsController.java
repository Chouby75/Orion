package com.openclassrooms.mddapi.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.openclassrooms.mddapi.data.services.TopicService;
import com.openclassrooms.mddapi.dto.MessageToReturn;
import com.openclassrooms.mddapi.dto.TopicsDto;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;




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

    @PutMapping("/subscribe")
    public MessageToReturn subscribeToTopic(@RequestParam Long topicId, @RequestParam Long userId) {
        Boolean result = topicService.subscribeToTopic(topicId, userId);
        MessageToReturn response = new MessageToReturn(null);
        if(result){
            response.setMessage("Subscription successful");
        } else {
            response.setMessage("Subscription failed");
        }
        return response;
    }

    @PutMapping("/unsubscribe")
    public MessageToReturn unsubscribeFromTopic(@RequestParam Long topicId, @RequestParam Long userId) {
        Boolean result = topicService.unsubscribeFromTopic(topicId, userId);
        MessageToReturn response = new MessageToReturn(null);
        if(result){
            response.setMessage("Unsubscription successful");
        } else {
            response.setMessage("Unsubscription failed");
        }
        return response;
    }
    
}
