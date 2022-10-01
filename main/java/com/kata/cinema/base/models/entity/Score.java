package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "score")
    private String score;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return Objects.equals(id, score1.id) && Objects.equals(score, score1.score) && Objects.equals(movie, score1.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, movie);
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", movie=" + movie +
                '}';
    }
}
