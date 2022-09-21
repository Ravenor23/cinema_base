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
@Table(name = "awards_ceremony_result")
public class AwardsCeremonyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "movie_id")
    private Long movieId;

    @Column(name = "nomination_id")
    private Long nominationId;

    @Column(name = "awards_ceremony_id")
    private Long awardsCeremonyId;

    @Column(name = "nomination_status")
    @Enumerated(EnumType.STRING)
    private String nominationStatus;
}
