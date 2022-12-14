package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.FormatType;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "movie_schedule")
public class MovieSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_schedule_seq")
    @SequenceGenerator(name = "mvsch_seq",
                       sequenceName = "mvsch_sequence",
                       initialValue = 1, allocationSize = 100)
    private Long id;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    private Set<MovieTicket> movieTicket;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Address> address;

    @Column(name = "format_type")
    @Enumerated(EnumType.STRING)
    private FormatType formatType;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "price")
    private Integer price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieSchedule that = (MovieSchedule) o;
        return id.equals(that.id) && movieTicket.equals(that.movieTicket) && address.equals(that.address)
            && formatType.equals(that.formatType) && dateTime.equals(that.dateTime) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
