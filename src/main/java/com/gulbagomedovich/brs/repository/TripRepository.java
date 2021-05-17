package com.gulbagomedovich.brs.repository;

import com.gulbagomedovich.brs.model.Agency;
import com.gulbagomedovich.brs.model.Bus;
import com.gulbagomedovich.brs.model.Stop;
import com.gulbagomedovich.brs.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Trip findByInitialStopAndDestinationStopAndBus(Stop initialStop, Stop destinationStop, Bus bus);

    List<Trip> findAllByInitialStopAndDestinationStop(Stop initialStop, Stop destinationStop);

    List<Trip> findAllByAgency(Agency agency);

}
