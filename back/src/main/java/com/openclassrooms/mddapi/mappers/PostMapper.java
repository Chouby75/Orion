package com.openclassrooms.mddapi.mappers;

import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.openclassrooms.mddapi.data.entity.CommentEntity;
import com.openclassrooms.mddapi.data.entity.PostEntity;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostSummarizeDto;

@Component
public class PostMapper {

    public static PostSummarizeDto mapToPostSummarizeDto(PostEntity postEntity) {
        if (postEntity == null) {
            return null;
        }

        PostSummarizeDto postToReturn = new PostSummarizeDto();
        postToReturn.setAuthor(postEntity.getAuthor());
        postToReturn.setTitle(postEntity.getTitle());
        postToReturn.setContent(postEntity.getContent());
        postToReturn.setCreatedAt(postEntity.getCreatedAt());

        return postToReturn;
    }

   public static PostDto mapToDto(PostEntity postEntity) {
        if (postEntity == null) {
            return null;
        }

        // On crée le DTO pour le post
        PostDto postToReturn = new PostDto();
        postToReturn.setAuthor(postEntity.getAuthor());
        postToReturn.setTitle(postEntity.getTitle());
        postToReturn.setContent(postEntity.getContent());
        postToReturn.setCreatedAt(postEntity.getCreatedAt());
        postToReturn.setId(postEntity.getId());
        postToReturn.setTopics(
            postEntity.getTopics().stream()
                .map(TopicsMapper::mapToSummarizeDto)
                .collect(Collectors.toSet())
        );
        if (postEntity.getComments() != null) {
            postToReturn.setComments(
                postEntity.getComments().stream()
                    .map(PostMapper::CommenttoDto)
                    .collect(Collectors.toList())
            );
        } else {
            postToReturn.setComments(Collections.emptyList()); // Si c'est null, on met une liste vide
        }

        return postToReturn;
    }

    public static CommentDto CommenttoDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new CommentDto(
            entity.getContent(),
            entity.getAuthor(),
            entity.getCreatedAt()
        );
    }
    
}
