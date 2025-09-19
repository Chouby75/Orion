package com.openclassrooms.mddapi.data.services;

import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.data.repo.PostRepository;
import com.openclassrooms.mddapi.data.entity.CommentEntity;
import com.openclassrooms.mddapi.data.entity.PostEntity;
import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostSummarizeDto;
import com.openclassrooms.mddapi.dto.TopicsSummarizeDto;
import com.openclassrooms.mddapi.mappers.PostMapper;
import java.util.stream.Collectors;
import com.openclassrooms.mddapi.data.repo.TopicsRepository;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepo;

    private final TopicsRepository topicsRepo;

    public PostService(PostRepository postRepo, TopicsRepository topicsRepo) {
        this.postRepo = postRepo;
        this.topicsRepo = topicsRepo;
    }

    public Boolean createPost(PostDto post) {

        if (post.getTitle() == null) {
            return false;
        }

        Set<TopicsSummarizeDto> topicsdto = post.getTopics();

        if (topicsdto == null || topicsdto.isEmpty()) {
            return false;
        }

        Set<TopicsEntity> topicsSet = new HashSet<>();
        topicsRepo.findAllByNameIn(topicsdto.stream()
                .map(TopicsSummarizeDto::getName)
                .collect(Collectors.toSet())).forEach(topicsSet::add);

        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());
        postEntity.setAuthor(post.getAuthor());
        postEntity.setTopics(topicsSet);

        PostEntity save = postRepo.save(postEntity);

        return save.getId() != null;
    }

    public List<PostSummarizeDto> getFeed() {
        return postRepo.findAllOrderByCreatedAtDesc().stream()
                .map(PostMapper::mapToPostSummarizeDto)
                .collect(Collectors.toList());
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

    public PostDto getPostById(Long id) {
        Optional<PostEntity> postOptional = postRepo.findById(id);
        if (postOptional.isEmpty()) {
            return null; // or throw an exception if preferred
        }

        PostDto postDtoById = PostMapper.mapToDto(postOptional.get());

        return postDtoById;
    }

}
