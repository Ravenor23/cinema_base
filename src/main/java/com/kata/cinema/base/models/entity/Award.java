package com.kata.cinema.base.models.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Table (name = "awards") // награда
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name; // название (к примеру, золотой глобус, оскар, сезар и т.д.)

    @Column(name = "description")
    private String description; // описание награды !!! В ReadMe нет описания данного поля

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name= "award_id", referencedColumnName = "id")
    private AwardsCeremony awardsCeremony;
}
