package com.openclassrooms.mddapi.data.repo;

import com.openclassrooms.mddapi.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepo extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByTitle(String title);
    Optional<UserEntity> findByContent(String content);
}
