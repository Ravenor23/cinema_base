package com.kata.cinema.base.models.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "available_online_movie")
public class AvailableOnlineMovie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aom_seq")
    @SequenceGenerator(name = "aom_seq",
            sequenceName = "aom_sequence",
            allocationSize = 1000)
    private Long id;

    @Column(name = "rental_price")
    private Integer rentalPrice;

    @Column(name = "buy_price")
    private Integer buyPrice;

    @Column(name = "available_plus")
    private Boolean availablePlus;

    @Column(name = "enabled")
    private Boolean enabled = true;

    @OneToOne(fetch = FetchType.LAZY)
    private Movie movie;

}
