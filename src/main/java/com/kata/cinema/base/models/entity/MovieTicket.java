package com.kata.cinema.base.models.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "movie_tickets")
public class MovieTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_ticket_seq")
    @SequenceGenerator(name = "mvt_seq",
                       sequenceName = "mvt_sequence",
                       initialValue = 1, allocationSize = 100)
    private Long id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Column(name = "end_show_date")
    private LocalDate endShowDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieTicket that = (MovieTicket) o;
        return id.equals(that.id) && movie.equals(that.movie) && endShowDate.equals(that.endShowDate);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
