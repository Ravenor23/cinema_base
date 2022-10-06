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
@Table(name = "awards_ceremony")
public class AwardsCeremony {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "awc_seq")
    @SequenceGenerator(name = "awc_seq",
            sequenceName = "awc_sequence",
            initialValue = 1, allocationSize = 20)
    private Long id;

    @Column(name = "date_event", unique = true)
    private String dateEvent;

    @Column(name = "place_event")
    private String placeEvent;

    @ToString.Exclude
    @Column(name = "award_id")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "awards_id")
    private Set<Award> awardId = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardsCeremony that = (AwardsCeremony) o;
        return id.equals(that.id) && dateEvent.equals(that.dateEvent) && placeEvent.equals(that.placeEvent);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
