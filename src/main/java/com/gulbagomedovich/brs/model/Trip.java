package com.gulbagomedovich.brs.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int fare;
    private int journeyTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "initialstop_id")
    private Stop initialStop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinationstop_id")
    private Stop destinationStop;

}
