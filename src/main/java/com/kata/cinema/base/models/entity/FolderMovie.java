package com.kata.cinema.base.models.entity;

import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.Privacy;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "folders_movies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FolderMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fm_seq")
    @SequenceGenerator(name = "fm_seq",
            sequenceName = "fm_sequence",
            initialValue = 1, allocationSize = 1000)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Privacy privacy;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FolderMovie that = (FolderMovie) o;
        return id.equals(that.id) && name.equals(that.name) && description.equals(that.description) && privacy.equals(that.privacy) && category.equals(that.category);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
