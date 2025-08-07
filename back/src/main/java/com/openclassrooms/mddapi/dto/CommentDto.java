package com.openclassrooms.mddapi.dto;
import java.time.LocalDateTime;

public class CommentDto {
    private String content;
    private String author;
    private LocalDateTime date;

    public CommentDto() {
    }
    public CommentDto(String content, String author, LocalDateTime date) {
        this.content = content;
        this.author = author;
        this.date = date;
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
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
