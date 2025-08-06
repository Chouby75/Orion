package com.openclassrooms.mddapi.dto;

import java.util.Set;

public class AccountDetailDto {
    private Long id;
    private String username;
    private String email;
    private Set<TopicsDto> topics;

    public AccountDetailDto() {
    }

    public AccountDetailDto(Long id, String username, String email, Set<TopicsDto> topics) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.topics = topics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TopicsDto> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicsDto> topics) {
        this.topics = topics;
    }
}
