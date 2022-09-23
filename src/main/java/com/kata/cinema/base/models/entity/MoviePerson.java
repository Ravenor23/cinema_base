package com.kata.cinema.base.models.entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Embeddable
@EqualsAndHashCode(of = {"id"})
@Table(name = "movie_person")
public class MoviePerson {



}
