package com.openclassrooms.mddapi.data.repo;

import com.openclassrooms.mddapi.data.entity.TopicsEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicsRepo extends CrudRepository<TopicsEntity, Long> {
    Optional<TopicsEntity> findByName(String name);
}
