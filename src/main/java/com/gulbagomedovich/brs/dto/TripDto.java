package com.gulbagomedovich.brs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.brs.model.Trip;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TripDto {

    private Long id;

    private int fare;
    private int journeyTime;

    private String busCode;
    private String agencyCode;
    private String initialStopCode;
    private String initialStopName;
    private String destinationStopCode;
    private String destinationStopName;

    public static TripDto toTripDto(Trip trip) {
        return new TripDto()
                .setId(trip.getId())
                .setFare(trip.getFare())
                .setJourneyTime(trip.getJourneyTime())
                .setBusCode(trip.getBus().getCode())
                .setAgencyCode(trip.getAgency().getCode())
                .setInitialStopCode(trip.getInitialStop().getCode())
                .setInitialStopName(trip.getInitialStop().getName())
                .setDestinationStopCode(trip.getDestinationStop().getCode())
                .setDestinationStopName(trip.getDestinationStop().getName());
    }

}
