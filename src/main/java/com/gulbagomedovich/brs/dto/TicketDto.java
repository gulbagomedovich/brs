package com.gulbagomedovich.brs.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gulbagomedovich.brs.model.Ticket;
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
public class TicketDto {

    private Long id;

    private int seatNumber;

    private boolean cancellable;

    private Date journeyDate;

    private String passengerName;
    private String passengerMobileNumber;
    private String busCode;
    private String initialStop;
    private String destinationStop;

    public static TicketDto toTicketDto(Ticket ticket) {
        return new TicketDto()
                .setId(ticket.getId())
                .setSeatNumber(ticket.getSeatNumber())
                .setCancellable(false)
                .setJourneyDate(ticket.getJourneyDate())
                .setPassengerName(ticket.getPassenger().getFullName())
                .setPassengerMobileNumber(ticket.getPassenger().getMobileNumber())
                .setBusCode(ticket.getTripSchedule().getTrip().getBus().getCode())
                .setInitialStop(ticket.getTripSchedule().getTrip().getInitialStop().getName())
                .setDestinationStop(ticket.getTripSchedule().getTrip().getDestinationStop().getName());
    }

}
