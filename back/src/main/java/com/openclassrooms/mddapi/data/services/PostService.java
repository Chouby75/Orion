package com.openclassrooms.mddapi.data.services;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.data.repo.PostRepo;
import com.openclassrooms.mddapi.data.entity.PostEntity;

import com.openclassrooms.mddapi.dto.PostDto;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }
    
    public String createPost(PostDto post) {
        // Convert PostDto to PostEntity
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setAuthor(post.getAuthor());
        postEntity.setComments(post.getComments());
        return "Post created successfully";
    }
        public PostDto[] getFeed() {
        PostDto[] posts = StreamSupport.stream(this.postRepo.findAll().spliterator(), false)
                .map(postEntity -> new PostDto(
                        postEntity.getTitle(),
                        postEntity.getContent(),
                        postEntity.getAuthor(),
                        postEntity.getCreatedAt(),
                        postEntity.getComments()))
                .toArray(PostDto[]::new);
        return posts;
        }

}
