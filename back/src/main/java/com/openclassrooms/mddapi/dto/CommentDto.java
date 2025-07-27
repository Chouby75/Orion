package com.openclassrooms.mddapi.dto;

import java.sql.Date;

public class CommentDto {
    private String content;
    private String author;
    private Date date;

    public CommentDto() {
    }
    public CommentDto(String content, String author, Date date) {
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
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
