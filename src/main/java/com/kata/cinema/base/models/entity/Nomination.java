package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "nomination") // номинация
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name; // название номинации

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomination_id", referencedColumnName = "id")
    private AwardsCeremonyResult awardsCeremonyResultFromNomination;
}
