package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "collections")
@ToString
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "col_seq")
    @SequenceGenerator(name = "col_seq",
            sequenceName = "col_sequence",
            initialValue = 1, allocationSize = 1000)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "enable")
    private Boolean enable;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "collection_movie",
            joinColumns = @JoinColumn(name = "collection_id"))
    private List<Movie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return id.equals(that.id) && name.equals(that.name) && enable.equals(that.enable);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}