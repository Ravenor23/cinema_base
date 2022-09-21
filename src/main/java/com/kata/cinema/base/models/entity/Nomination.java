package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "nomination")
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomination_id", referencedColumnName = "id")
    private AwardsCeremonyResult awardsCeremonyResultFromNomination;

    private String name;
}
