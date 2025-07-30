package com.openclassrooms.mddapi.mappers;

import java.util.Collections;
import java.util.stream.Collectors;

import com.openclassrooms.mddapi.data.entity.CommentEntity;
import com.openclassrooms.mddapi.data.entity.PostEntity;
import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.data.entity.UserEntity;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.UserDto;


public class PostMapper {
    // This class will contain methods to map entities to DTOs
    // For example, mapping UserEntity to UserDto

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

        // ✅ C'EST LA PARTIE QUI MANQUAIT ✅
        // On s'occupe des commentaires
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

    public static UserEntity mapToEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        return userEntity;
    }
    
}
