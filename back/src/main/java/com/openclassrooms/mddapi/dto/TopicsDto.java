package com.openclassrooms.mddapi.dto;

public class TopicsDto {

    private Long id;
    private String name;

    // --- Constructeurs ---

    public TopicsDto() {
        // Constructeur par défaut, essentiel pour la désérialisation
    }

    public TopicsDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    // --- Getters et Setters ---

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