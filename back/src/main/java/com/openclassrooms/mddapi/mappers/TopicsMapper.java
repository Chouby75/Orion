package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.data.entity.TopicsEntity;
import com.openclassrooms.mddapi.dto.TopicsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // On le déclare comme un composant Spring pour pouvoir l'injecter partout
public class TopicsMapper {

    /**
     * Convertit une entité TopicsEntity en son DTO.
     * @param entity L'entité à convertir.
     * @return Le DTO correspondant, ou null si l'entité est null.
     */
    public TopicsDto toDto(TopicsEntity entity) {
        if (entity == null) {
            return null;
        }

        TopicsDto dto = new TopicsDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    /**
     * Convertit un DTO TopicsDto en son entité.
     * @param dto Le DTO à convertir.
     * @return L'entité correspondante, ou null si le DTO est null.
     */
    public TopicsEntity toEntity(TopicsDto dto) {
        if (dto == null) {
            return null;
        }

        TopicsEntity entity = new TopicsEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        // On ne touche pas aux listes 'posts' et 'subscribers',
        // car elles sont gérées par les relations JPA.
        return entity;
    }

    /**
     * Convertit une liste d'entités en une liste de DTOs.
     * @param entities La liste d'entités.
     * @return La liste de DTOs.
     */
    public List<TopicsDto> toDtos(List<TopicsEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Convertit une liste de DTOs en une liste d'entités.
     * @param dtos La liste de DTOs.
     * @return La liste d'entités.
     */
    public List<TopicsEntity> toEntity(List<TopicsDto> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}