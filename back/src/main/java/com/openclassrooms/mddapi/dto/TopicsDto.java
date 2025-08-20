package com.openclassrooms.mddapi.dto;

public class TopicsDto {

    private Long id;
    private String name;
    private String description;
    private Boolean isSubscribed;

    public TopicsDto() {
    }

    public TopicsDto(Long id, String name, String description, Boolean isSubscribed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isSubscribed = isSubscribed;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsSubscribed() {
        return isSubscribed;
    }
    
    public void setIsSubscribed(Boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }
}