package com.openclassrooms.mddapi.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.PostDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("/feed")
@RestController
public class FeedController {
    
    @PostMapping()
    public String createPost(@RequestBody String entity) {
        
        return "Post created successfully";
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody String entity) {
        return "Comment created successfully";
    }

    @GetMapping()
    public PostDto[] getFeed(@RequestParam String param) {
        return new PostDto[0];
    }

    @GetMapping("/{postId}")
    public PostDto getPost(@RequestParam String title) {
        return new PostDto();
    }


}
