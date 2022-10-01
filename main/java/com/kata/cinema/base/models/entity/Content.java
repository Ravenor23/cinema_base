package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "content_url")
    private String contentUrl;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id) && Objects.equals(type, content.type)
                && Objects.equals(movie, content.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, movie);
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", movie=" + movie +
                '}';
    }
}