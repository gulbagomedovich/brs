package com.gulbagomedovich.brs.repository;

import com.gulbagomedovich.brs.model.Trip;
import com.gulbagomedovich.brs.model.TripSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TripScheduleRepository extends JpaRepository<TripSchedule, Long> {

    TripSchedule findByTripAndTripDate(Trip trip, Date tripDate);

}
