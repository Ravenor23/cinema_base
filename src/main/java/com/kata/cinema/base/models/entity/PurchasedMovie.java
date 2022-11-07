package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.PurchaseType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "purchased_movies")
public class PurchasedMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchase_seq")
    @SequenceGenerator(name = "purchase_seq",
            sequenceName = "purchase_sequence",
            allocationSize = 200)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @JoinColumn(name = "available_online_movie_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AvailableOnlineMovie movie;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "purchase_type")
    private PurchaseType purchase;
}
