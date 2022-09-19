package com.kata.cinema.base.models.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "movie_id")
    private String movieId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "score")
    private String score;

    public Score(long id, String movieId, String userId, String score) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Score() {
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
        Score score1 = (Score) o;
        return id == score1.id && Objects.equals(movieId, score1.movieId) && Objects.equals(userId, score1.userId) && Objects.equals(score, score1.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movieId, userId, score);
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", movieId='" + movieId + '\'' +
                ", userId='" + userId + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
