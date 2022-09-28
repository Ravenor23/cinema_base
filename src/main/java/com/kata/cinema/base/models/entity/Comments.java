package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    private String text;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    News news;

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comments comments = (Comments) o;
        return Objects.equals(id, comments.id) && Objects.equals(text, comments.text) && Objects.equals(date, comments.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, date);
    }
}
