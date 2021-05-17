package com.gulbagomedovich.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int seatNumber;

    private boolean cancellable;

    @Temporal(TemporalType.TIMESTAMP)
    private Date journeyDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User passenger;

    @ManyToOne
    @JoinColumn(name = "tripschedule_id")
    private TripSchedule tripSchedule;

}
