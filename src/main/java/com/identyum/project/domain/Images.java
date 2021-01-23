package com.identyum.project.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String owner;

    @ManyToMany
    @JoinTable(name = "shared_with", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<User> users = new HashSet<>();

    private String url;

    public Images() {
    }

    public Images(String title, String owner, String url) {
        this.title = title;
        this.owner = owner;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<User> getSharedWith() {
        return users;
    }

    public void setSharedWith(Set<User> sharedWith) {
        this.users = sharedWith;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                ", users=" + users +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Images images = (Images) o;
        return Objects.equals(id, images.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
