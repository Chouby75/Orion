package com.openclassrooms.mddapi.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.data.services.PostService;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.MessageToReturn;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/feed")
@RestController
public class FeedController {

    private final PostService postService;

    public FeedController(PostService postService){
        this.postService = postService;
    }
    
    @PostMapping()
    public MessageToReturn createPost(@RequestBody PostDto entity, Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return new MessageToReturn("User not authenticated or author not specified");
        }
        entity.setAuthor(username);
        Boolean result = postService.createPost(entity);

        MessageToReturn response = new MessageToReturn(null);

        if(result){
            response.setMessage("Post successfully created");
        } else {
            response.setMessage("Post encounter a problem when create");
        }

        return response;
    }

    @PostMapping("/{postId}/comment")
    public MessageToReturn createComment(@PathVariable Long postId, @RequestBody CommentDto comment, Authentication authentication) {
        String username = authentication.getName();
        if (username == null) {
            return new MessageToReturn("User not authenticated or author not specified");
        }
        comment.setAuthor(username);
        Boolean result = postService.postComment(postId, comment);

        MessageToReturn response = new MessageToReturn(null);

        if(result){
            response.setMessage("Post successfully commented");
        } else {
            response.setMessage("Post encounter a problem when commented");
        }

        return response;
    }

    @GetMapping()
    public PostDto[] getFeed() {
        PostDto[] posts = postService.getFeed();
        return posts;
    }

    @GetMapping("/{postId}")
    public PostDto getPost(@PathVariable  Long postId) {
        if (postId ==null) {
            return null;
        }
        PostDto postToReturn = postService.getPostById(postId);
        return postToReturn;
    }


}
