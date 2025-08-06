package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.dto.TopicsDto;
import com.openclassrooms.mddapi.dto.TopicsSummarizeDto;

import org.springframework.stereotype.Component;

@Component 
public class TopicsMapper {

    public static TopicsDto mapToDto(TopicsEntity topicsEntity) {
        if (topicsEntity == null) {
            return null;
        }

        TopicsDto topicsToReturn = new TopicsDto();
        topicsToReturn.setId(topicsEntity.getId());
        topicsToReturn.setName(topicsEntity.getName());
        topicsToReturn.setDescription(topicsEntity.getDescription());

        return topicsToReturn;
    }

    public static TopicsSummarizeDto mapToSummarizeDto(TopicsEntity topicsEntity) {
        if (topicsEntity == null) {
            return null;
        }

        TopicsSummarizeDto topicsToReturn = new TopicsSummarizeDto();
        topicsToReturn.setId(topicsEntity.getId());
        topicsToReturn.setName(topicsEntity.getName());

        return topicsToReturn;
    }

}