package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.data.entity.CommentEntity;
import com.openclassrooms.mddapi.data.entity.PostEntity;
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
        PostDto postToReturn = new PostDto();
        postToReturn.setAuthor(postEntity.getAuthor());
        postToReturn.setTitle(postEntity.getTitle());
        postToReturn.setContent(postEntity.getContent());
        postToReturn.setCreatedAt(postEntity.getCreatedAt());
        return postToReturn;
    }

    public static CommentDto CommenttoDto(CommentEntity entity) {
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
