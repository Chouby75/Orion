package com.openclassrooms.mddapi.data.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.data.repo.TopicsRepo;
import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.mappers.TopicsMapper;

@Service
public class TopicService {

    private final TopicsRepo topicsRepo;

    public TopicService(TopicsRepo topicsRepo) {
        this.topicsRepo = topicsRepo;
    }

    public Set<TopicsDto> getTopics() {

        Iterable<TopicsEntity> topicsEntities = topicsRepo.findAll();
        
        Set<TopicsEntity> topicsSet = new HashSet<>();
        topicsEntities.forEach(topicsSet::add);

        return topicsSet.stream()
            .map(TopicsMapper::mapToDto)
            .collect(Collectors.toSet());
    }
}