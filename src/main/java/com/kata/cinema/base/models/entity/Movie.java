package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq")
    @SequenceGenerator(name = "m_seq",
            sequenceName = "m_sequence",
            initialValue = 1, allocationSize = 5000)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "countries")
    private String countries;

    @Column(name = "data_release")
    private LocalDate dataRelease;

    @Column(name = "rars")
    private String rars;

    @Column(name = "mpaa")
    private String mpaa;

    @Column(name = "time")
    private String time;

    @Column(name = "description")
    private String description;

    @Column(name = "origin_name")
    private String originName;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<com.kata.cinema.base.models.entity.Genre> genres;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie that = (Movie) o;
        return id.equals(that.id) && name.equals(that.name)
                && description.equals(that.description) && countries.equals(that.countries) && dataRelease.equals(that.dataRelease) &&
                rars.equals(that.rars) && mpaa.equals(that.mpaa) && time.equals(that.time) && originName.equals(that.originName);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}

