package com.gulbagomedovich.brs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.brs.model.TripSchedule;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripScheduleDto {

    private Long id;

    private int availableSeats;

    private Date tripDate;

    private Long tripId;

    private int fare;
    private int journeyTime;

    private String busCode;
    private String initialStop;
    private String destinationStop;

    public static TripScheduleDto toTripScheduleDto(TripSchedule tripSchedule) {
        return new TripScheduleDto()
                .setId(tripSchedule.getId())
                .setAvailableSeats(tripSchedule.getAvailableSeats())
                .setTripDate(tripSchedule.getTripDate())
                .setTripId(tripSchedule.getTrip().getId())
                .setFare(tripSchedule.getTrip().getFare())
                .setJourneyTime(tripSchedule.getTrip().getJourneyTime())
                .setBusCode(tripSchedule.getTrip().getBus().getCode())
                .setInitialStop(tripSchedule.getTrip().getInitialStop().getName())
                .setDestinationStop(tripSchedule.getTrip().getDestinationStop().getName());
    }

}
