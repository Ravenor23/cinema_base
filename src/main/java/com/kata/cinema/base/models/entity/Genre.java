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
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "g_seq")
    @SequenceGenerator(name = "g_seq",
            sequenceName = "g_sequence",
            initialValue = 1, allocationSize = 30)
    private Long id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre that = (Genre) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
