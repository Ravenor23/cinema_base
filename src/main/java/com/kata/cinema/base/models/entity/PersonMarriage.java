package com.kata.cinema.base.models.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "persons_marriage")
public class PersonMarriage {


    @Column(name = "marriageStatus")
    private String marriageStatus;

    private Long human_id;

    @EmbeddedId
    private Human human;


    @Embeddable
    class Human implements Serializable {
        @Column
        long personId;

    }

}
