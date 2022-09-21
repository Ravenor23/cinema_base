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
@Table(name = "awards")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "award_id", referencedColumnName = "id")
    private AwardsCeremony awardsCeremony;

    private String name;

    private String description;

}
