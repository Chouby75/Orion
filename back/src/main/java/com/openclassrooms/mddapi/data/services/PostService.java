package com.openclassrooms.mddapi.data.services;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.data.repo.PostRepo;
import com.openclassrooms.mddapi.data.entity.PostEntity;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mappers.PostMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }
    
    public Boolean createPost(PostDto post) {

        if(post.getTitle() == null || post.getAuthor() == null){
            return false;
        }

        PostEntity postEntity = new PostEntity();
            postEntity.setTitle(post.getTitle());
            postEntity.setContent(post.getContent());
            postEntity.setAuthor(post.getAuthor());

            PostEntity save = postRepo.save(postEntity);
            
        return save.getId() != null;
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
// FAIRE UN COMMENT ENTITY AVEC UNE RELATION
    // public Boolean postComment(Long id, CommentDto comment) {
    //     Optional<PostEntity> postOptional = postRepo.findById(id);
    //     PostEntity post = postOptional.get();
    //     List<CommentDto> commentCopy = post.getComments();
    //     PostEntity postCommented = post.setComments(comment);
        

    //     return true;
    // }

    public PostDto getPostById(Long id){
        PostEntity postOptional = postRepo.findById(id).get();
        PostDto postDtoById = PostMapper.mapToDto(postOptional);
        
        return postDtoById;
    }

}
