package com.openclassrooms.mddapi.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PostDto {

    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private List<CommentDto> comments;

    public PostDto() {
    }

    public PostDto(String title, String content, String author, LocalDateTime createdAt, List<CommentDto> comments) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

}
