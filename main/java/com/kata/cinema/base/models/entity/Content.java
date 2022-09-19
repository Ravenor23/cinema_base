package com.kata.cinema.base.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "movie_id")
    private int movieId;

    @Column(name = "content_url")
    private String contentUrl;

    @Column(name = "type")
    private String type;

    public Content(long id, int movieId, String contentUrl, String type) {
        this.id = id;
        this.movieId = movieId;
        this.contentUrl = contentUrl;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Content() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return id == content.id && movieId == content.movieId && Objects.equals(contentUrl, content.contentUrl)
                && Objects.equals(type, content.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, contentUrl, type);
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", contentUrl='" + contentUrl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}