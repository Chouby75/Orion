package com.openclassrooms.mddapi.data.services;

import java.util.List;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.data.repo.TopicsRepo;
import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.mappers.TopicsMapper;

@Service
public class TopicService {

    private final TopicsRepo topicsRepo;
    private final TopicsMapper topicsMapper;

    public TopicService(TopicsRepo topicsRepo, TopicsMapper topicsMapper) {
        this.topicsRepo = topicsRepo;
        this.topicsMapper = topicsMapper;
    }

    public List<TopicsDto> getTopics() {
        Iterable<TopicsEntity> topicsEntities = topicsRepo.findAll();
        List<TopicsEntity> topicsEntitiesList = (List<TopicsEntity>) topicsEntities;
        return topicsMapper.toDtos(topicsEntitiesList);
    }
}