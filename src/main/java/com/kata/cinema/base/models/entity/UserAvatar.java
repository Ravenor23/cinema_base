package com.kata.cinema.base.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_avatar")
@NoArgsConstructor
@AllArgsConstructor
public class UserAvatar {

    @Id
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @Getter
    @Setter
    private User user;

    @Getter
    @Setter
    private String avatar_url;
}
