package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "awards_ceremony_result")
public class AwardsCeremonyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "awcr_seq")
    @SequenceGenerator(name = "awcr_seq",
            sequenceName = "awcr_sequence",
            initialValue = 1, allocationSize = 30)
    private Long id;

    //TODO сделать нормальную связь с персоной
    @Column(name = "person_id")
    private Long personId;

    //TODO сделать нормальную связь с фильмом
    @Column(name = "movie_id")
    private Long movieId;

    @ToString.Exclude
    @OneToMany
    //TODO сделать одностороннию связь с другой стороны
    private Set<Nomination> nominations;

    @ToString.Exclude
    @OneToMany
    //TODO сделать одностороннию связь с другой стороны
    private Set<AwardsCeremony> awardsCeremonies;

    @Column(name = "nomination_status")
    private String nominationStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardsCeremonyResult that = (AwardsCeremonyResult) o;
        return id.equals(that.id) && personId.equals(that.personId) && movieId.equals(that.movieId)
                && nominationStatus.equals(that.nominationStatus);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
