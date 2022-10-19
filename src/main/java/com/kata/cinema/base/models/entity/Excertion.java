package com.kata.cinema.base.models.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "excertion")
public class Excertion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Lob
    private String description;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "movies_id")
    private Movie movie;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;
}