package com.openclassrooms.mddapi.data.repo;

import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.dto.TopicsDto;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TopicsRepo extends CrudRepository<TopicsEntity, Long> {
    Optional<TopicsEntity> findByName(String name);

    Optional<TopicsEntity> findById(Long id);

    Iterable<TopicsEntity> findAllByNameIn(Iterable<String> names);

    @Query("SELECT new com.openclassrooms.mddapi.dto.TopicsDto(t.id, t.name, t.description, (COUNT(u) > 0)) " +
            "FROM TopicsEntity t " +
            "LEFT JOIN t.subscribers u ON u.id = :userId " +
            "GROUP BY t.id, t.name, t.description " +
            "ORDER BY t.id ASC")
    Set<TopicsDto> findAllWithSubscriptionStatus(@Param("userId") Long userId);

}
