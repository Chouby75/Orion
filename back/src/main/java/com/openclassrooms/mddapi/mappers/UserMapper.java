package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.data.entity.UserEntity;
import com.openclassrooms.mddapi.dto.UserDto;

public class UserMapper {
    // This class will contain methods to map entities to DTOs
    // For example, mapping UserEntity to UserDto

    public static UserDto mapToDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDto(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail());
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
