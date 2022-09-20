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
@Setter
@Getter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String data;

    private String title;

    @Column(name = "html_body")
    private String htmlBody;

    @Enumerated(value = EnumType.STRING)
    private Rubric rubric;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
