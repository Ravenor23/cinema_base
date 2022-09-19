package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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
