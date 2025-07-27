package com.openclassrooms.mddapi.data.entity;
import com.openclassrooms.mddapi.dto.CommentDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import com.openclassrooms.mddapi.mappers.PostMapper;

@Entity
@Table(name = "posts")
public class PostEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "topics")
    private String[] topics;

    @Column(name = "content")
    private String content;

    @Column(name = "author")
    private String author;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // C'est une vraie relation JPA maintenant !
    @JoinColumn(name = "post_id") // Clé étrangère dans la table des commentaires
    private List<CommentEntity> comments = new ArrayList<>(); // Initialisation !

    public PostEntity() {
    }

   public PostEntity(Long id, String title, String content, String author, LocalDateTime createdAt, List<CommentEntity> comments, String[] topics) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.comments = comments;
        this.topics = topics;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
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
        return comments.stream()
                   .map(PostMapper::CommenttoDto) // ou .map(comment -> toDto(comment))
                   .collect(Collectors.toList());
    }

    public void setComments(CommentEntity comments) {
        this.comments.add(comments);
    }

    
    
}
