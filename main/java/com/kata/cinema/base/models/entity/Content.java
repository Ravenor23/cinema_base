package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", contentUrl='" + contentUrl + '\'' +
                ", type='" + type + '\'' +
                ", movie=" + movie +
                '}';
    }
}