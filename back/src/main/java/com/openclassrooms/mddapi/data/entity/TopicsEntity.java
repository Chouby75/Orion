package com.openclassrooms.mddapi.data.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="topics")
public class TopicsEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "subscriptions")
    private Set<UserEntity> subscribers = new HashSet<>();

    @ManyToMany(mappedBy = "topics")
    private Set<PostEntity> posts = new HashSet<>();

    public TopicsEntity() {
    }

    public TopicsEntity(Long id, String name, Set<UserEntity> subscribers, Set<PostEntity> posts) {
        this.id = id;
        this.name = name;
        this.subscribers = subscribers;
        this.posts = posts;
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

    public Set<UserEntity> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(Set<UserEntity> subscribers) {
        this.subscribers = subscribers;
    }

    public Set<PostEntity> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostEntity> posts) {
        this.posts = posts;
    }
}
