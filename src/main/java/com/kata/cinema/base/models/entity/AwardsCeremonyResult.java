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

    @Column(name = "person_id")

    private Long personId;

    @Column(name = "movie_id")
    private Long movieId;

    @ToString.Exclude
    @Column(name = "nomination_id")
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "nomination_id")
    private Set<Nomination> nominations;

    @ToString.Exclude
    @Column(name = "awards_ceremony_id")
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "awards_ceremony_id")
    private Set<AwardsCeremony> awardsCeremonies;

    @Column(name = "nomination_status")
    @Enumerated(EnumType.STRING)
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
