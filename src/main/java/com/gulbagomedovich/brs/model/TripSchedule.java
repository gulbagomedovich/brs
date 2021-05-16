package com.gulbagomedovich.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "tripschedules")
public class TripSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int availableSeats;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tripDate;

    @OneToMany(mappedBy = "tripSchedule")
    private Set<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

}
