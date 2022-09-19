package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "awards_ceremony") // церемония награждения
public class AwardsCeremony {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date_event", unique = true)
    private String dateEvent; // дата проведения

    @Column(name = "place_event")
    private String placeEvent; // место проведения

    @Column(name = "award_id")
    private long awardId; // уникальный идентификатор награды

    @OneToMany(mappedBy = "awardsCeremony")
    private Set<Award> awardSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_ceremony_id", referencedColumnName = "id")
    private AwardsCeremonyResult awardsCeremonyResultFromAwardCeremony;
}
