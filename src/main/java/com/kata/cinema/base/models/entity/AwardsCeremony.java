package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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
    private Long id;

    @Column(name = "date_event", unique = true)
    private String dateEvent;

    @Column(name = "place_event")
    private String placeEvent;

    @Column(name = "award_id")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "awards_id")
    private Set<Award> awardId = new HashSet<>();
}
