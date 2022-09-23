package com.kata.cinema.base.models.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Embeddable
@EqualsAndHashCode(of = {"id"})
@Table(name = "movie_person")
public class MoviePerson {
    @Getter
    @Setter
    @NoArgsConstructor
    @Embeddable
    @EqualsAndHashCode(of = {"personId", "movieId"})
    public static class Id implements Serializable {

        @Column(name = "person_id")
        private Long personId;

        @Column(name = "movie_id")
        private Long movieId;

        public Id(Long personId, Long movieId) {
            this.personId = personId;
            this.movieId = movieId;
        }
    }
    @EmbeddedId
    private Id id;

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @ManyToOne(targetEntity = Movie.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", insertable = false, updatable = false)
    private Movie movie;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "movie_person_to_profession",
            joinColumns = {@JoinColumn(name = "movie_id", insertable = false, updatable = false),
                    @JoinColumn(name = "person_id", insertable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "profession_id")})
    private Set<Profession> professions;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_character")
    private TypeCharacter type;

    @Column(name = "name_role")
    private String nameRole;
}




