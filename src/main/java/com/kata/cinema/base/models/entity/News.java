package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.Rubric;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private String data;

    @Setter
    @Getter
    private String title;

    @Setter
    @Getter
    private String html_body;

    @Enumerated(value = EnumType.STRING)
    @Setter
    @Getter
    private Rubric rubric;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Setter
    @Getter
    private User user;
}
