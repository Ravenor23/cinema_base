package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "countries")
    private String countries;

    @Column(name = "data_release")
    private String dataRelease;

    @Column(name = "rars")
    @Enumerated(EnumType.STRING)
    private RARS rars;

    @Column(name = "mpaa")
    @Enumerated(EnumType.STRING)
    private MPAA mpaa;

    @Column(name = "time")
    private String time;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "origin_name")
    private String originName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "content")
    private List<Content> contents;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "score")
    private List<Score> scores;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "collection_id"))
    private List<Collection> collections;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "news_movie",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "news_id"))
//    private List<News> news;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "folder_movies_to_movie",
//            joinColumns = @JoinColumn(name = "movies_id"),
//            inverseJoinColumns = @JoinColumn(name = "folder_id"))
//    private List<FoldersMovie> foldersMovies;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "movie_person",
//            joinColumns = @JoinColumn(name = "movie_id"),
//            inverseJoinColumns = @JoinColumn(name = "person_id"))
//    private List<Person> persons;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "awards_ceremony_result",
//            joinColumns = @JoinColumn(name = "awards_ceremony_id"),
//            inverseJoinColumns = @JoinColumn(name = "news_id"))
//    private List<AwardsCeremony> awardsCeremony;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) && Objects.equals(name, movie.name)
                && Objects.equals(countries, movie.countries) && rars == movie.rars && mpaa == movie.mpaa
                && Objects.equals(time, movie.time) && Objects.equals(description, movie.description)
                && Objects.equals(type, movie.type) && Objects.equals(contents, movie.contents)
                && Objects.equals(scores, movie.scores) && Objects.equals(collections, movie.collections)
                && Objects.equals(genres, movie.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, countries, rars, mpaa, time, description, type, contents, scores, collections, genres);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countries='" + countries + '\'' +
                ", rars=" + rars +
                ", mpaa=" + mpaa +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", contents=" + contents +
                ", scores=" + scores +
                ", collections=" + collections +
                ", genres=" + genres +
                '}';
    }
}

