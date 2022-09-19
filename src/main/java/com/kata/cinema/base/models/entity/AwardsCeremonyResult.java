package com.kata.cinema.base.models.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "awards_ceremony_result") //результаты церемонии награждения
public class AwardsCeremonyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "person_id")
    private long personId; // уникальный идентификатор пользователя

    @Column(name = "movie_id")
    private long movieId; // уникальный идентификатор фильма

    @Column(name = "nomination_id")
    private long nominationId; // уникальный идентификатор номинации

    @Column(name = "awards_ceremony_id")
    private long awardsCeremonyId; // уникальный идентификатор церемонии награждения

    @Column(name = "nomination_status")
    private String nominationStatus; // статус номинации, может принимать следующие параметры: NOMINATED, WINNER

    @OneToMany(mappedBy = "awardsCeremonyResultFromNomination")
    private Set<Nomination> nominationSet = new HashSet<>();

    @OneToMany(mappedBy = "awardsCeremonyResultFromAwardCeremony")
    private Set<AwardsCeremony> awardsCeremonySet = new HashSet<>();

// + 2 мапы на person_id и movie_id
}
