package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.Privacy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "folders_movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FolderMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Privacy privacy;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
