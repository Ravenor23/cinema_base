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
@Table(name = "awards_ceremony_result")
public class AwardsCeremonyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "awcr_seq")
    @SequenceGenerator(name = "awcr_seq",
            sequenceName = "awcr_sequence",
            initialValue = 1, allocationSize = 30)
    private Long id;

    @Column(name = "nomination_status")
    private String nominationStatus;


    //TODO сделать нормальную связь с персоной
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "persons")
    private Person persons;

    //TODO сделать нормальную связь с фильмом
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "movies")
    private Movie movies;

    //TODO сделать одностороннию связь с другой стороны
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "nomination")
    private Nomination nominations;

    //TODO сделать одностороннию связь с другой стороны
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "awards_ceremony")
    private AwardsCeremony awardsCeremonies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardsCeremonyResult that = (AwardsCeremonyResult) o;
        return Objects.equals(id, that.id) && Objects.equals(persons, that.persons)
                && Objects.equals(movies, that.movies) && Objects.equals(nominations, that.nominations)
                && Objects.equals(awardsCeremonies, that.awardsCeremonies)
                && Objects.equals(nominationStatus, that.nominationStatus);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
