package com.openclassrooms.mddapi.dto;

public class TopicsSummarizeDto {

    private Long id;
    private String name;

    // --- Constructeurs ---

    public TopicsSummarizeDto() {
        // Constructeur par défaut, essentiel pour la désérialisation
    }

    public TopicsSummarizeDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}