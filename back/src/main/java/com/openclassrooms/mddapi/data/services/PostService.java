package com.openclassrooms.mddapi.data.services;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.data.repo.PostRepo;
import com.openclassrooms.mddapi.data.entity.CommentEntity;
import com.openclassrooms.mddapi.data.entity.PostEntity;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.mappers.PostMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
                        postEntity.getComments().stream()
                                .map(PostMapper::CommenttoDto)
                                .collect(Collectors.toList())))
                .toArray(PostDto[]::new);
        return posts;
    }

    public Boolean postComment(Long id, CommentDto comment) {
    try {
        PostEntity post = postRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post non trouvé !"));
        
        CommentEntity newComment = new CommentEntity();
        newComment.setContent(comment.getContent());
        newComment.setAuthor(comment.getAuthor());

        post.addComment(newComment);
        
        PostEntity postSaved = postRepo.save(post);
        return true;

    } catch (Exception e) {
        return false;
    }
}

    public PostDto getPostById(Long id){
        PostEntity postOptional = postRepo.findById(id).get();
        PostDto postDtoById = PostMapper.mapToDto(postOptional);
        
        return postDtoById;
    }

}
