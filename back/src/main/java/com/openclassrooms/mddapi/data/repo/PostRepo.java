package com.openclassrooms.mddapi.data.repo;

import com.openclassrooms.mddapi.data.entity.PostEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends CrudRepository<PostEntity, Long> {
    Optional<PostEntity> findByTitle(String title);

    Optional<PostEntity> findByContent(String content);

    @Query("SELECT p FROM PostEntity p ORDER BY p.createdAt DESC")
    List<PostEntity> findAllOrderByCreatedAtDesc();
}
