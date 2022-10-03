package com.kata.cinema.base.models.entity;

import liquibase.pro.packaged.J;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persons_marriage")
public class PersonMarriage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "marriageStatus")
    private String marriageStatus;

    @ManyToOne
    @JoinColumn(name = "human_id")
    private Person human;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;



}
