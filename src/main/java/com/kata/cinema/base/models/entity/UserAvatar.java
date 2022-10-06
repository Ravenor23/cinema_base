package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_avatar")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "us_seq")
    @SequenceGenerator(name = "us_seq",
            sequenceName = "us_sequence",
            initialValue = 1, allocationSize = 3000)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAvatar that = (UserAvatar) o;
        return avatarUrl.equals(that.avatarUrl);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
