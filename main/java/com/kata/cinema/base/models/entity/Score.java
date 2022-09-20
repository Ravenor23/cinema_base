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

    @Column(name = "movie_id")
    private String movieId;

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
        return id == score1.id && Objects.equals(movieId, score1.movieId) && Objects.equals(userId, score1.userId) && Objects.equals(score, score1.score) && Objects.equals(movie, score1.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, userId, score, movie);
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", movieId='" + movieId + '\'' +
                ", userId='" + userId + '\'' +
                ", score='" + score + '\'' +
                ", movie=" + movie +
                '}';
    }
}
