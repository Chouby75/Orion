package com.openclassrooms.mddapi.data.repo;

import com.openclassrooms.mddapi.data.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends CrudRepository<CommentEntity, Long> {
    Optional<CommentEntity> findByContent(String content);
}
