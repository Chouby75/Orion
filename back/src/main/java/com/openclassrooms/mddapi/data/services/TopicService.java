package com.openclassrooms.mddapi.data.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.data.entity.UserEntity;
import com.openclassrooms.mddapi.data.repo.TopicsRepo;
import com.openclassrooms.mddapi.data.repo.UserRepo;
import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.mappers.TopicsMapper;

@Service
public class TopicService {

    private final TopicsRepo topicsRepo;
    private final UserRepo userRepo;

    public TopicService(TopicsRepo topicsRepo, UserRepo userRepo) {
        this.topicsRepo = topicsRepo;
        this.userRepo = userRepo;
    }

    public Set<TopicsDto> getTopics(UserEntity user) {

        Iterable<TopicsEntity> topicsEntities = topicsRepo.findAll();

        Set<TopicsEntity> topicsSet = new HashSet<>();
        topicsEntities.forEach(topicsSet::add);
        Set<TopicsDto> topicsDto = topicsSet.stream()
                .map(TopicsMapper::mapToDto)
                .collect(Collectors.toSet());

        Set<Long> subscribedTopicIds = user.getSubscriptions().stream()
                .map(TopicsEntity::getId)
                .collect(Collectors.toSet());

        topicsDto.forEach(dto -> {
            dto.setIsSubscribed(subscribedTopicIds.contains(dto.getId()));
        });

        return topicsDto;
    }

    public Boolean subscribeToTopic(Long topicId, Long userId) {
        TopicsEntity topic = topicsRepo.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Topic not found with id: " + topicId));

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        user.getSubscriptions().add(topic);
        UserEntity userSaved = userRepo.save(user);
        return userSaved != null;
    }

    public Boolean unsubscribeFromTopic(Long topicId, Long userId) {
        TopicsEntity topic = topicsRepo.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Topic not found with id: " + topicId));

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        user.getSubscriptions().remove(topic);
        UserEntity userSaved = userRepo.save(user);
        return userSaved != null;
    }
}