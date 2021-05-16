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
@Table(name = "buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String model;

    private int capacity;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @OneToMany(mappedBy = "bus")
    private Set<Trip> trips;

}
