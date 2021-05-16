package com.gulbagomedovich.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "stops")
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;
    private String derails;

    @OneToMany(mappedBy = "initialStop")
    private Set<Trip> tripsByInitialStop;

    @OneToMany(mappedBy = "destinationStop")
    private Set<Trip> tripsByDestinationStop;

}
