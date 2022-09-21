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
@EqualsAndHashCode
@Table(name = "awards_ceremony")
public class AwardsCeremony {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awards_ceremony_id", referencedColumnName = "id")
    private AwardsCeremonyResult awardsCeremonyResultFromAwardCeremony;

    @Column(name = "date_event", unique = true)
    private String dateEvent;

    @Column(name = "place_event")
    private String placeEvent;

    @Column(name = "award_id")
    private Long awardId;

}
