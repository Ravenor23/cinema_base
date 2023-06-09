package com.kata.cinema.base.models.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "score", uniqueConstraints = {@UniqueConstraint(name = "score_id", columnNames = "id"),
        @UniqueConstraint(name = "uk_user_movie", columnNames = {"movie_id", "user_id"})})
@ToString
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sc_seq")
    @SequenceGenerator(name = "sc_seq",
            sequenceName = "sc_sequence",
            allocationSize = 100)
    private Long id;

    @Column(name = "score")
    private Integer score;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score that = (Score) o;
        return id.equals(that.id) && score.equals(that.score) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
