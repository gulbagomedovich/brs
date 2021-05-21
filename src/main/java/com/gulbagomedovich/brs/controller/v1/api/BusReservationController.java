package com.gulbagomedovich.brs.controller.v1.api;

import com.gulbagomedovich.brs.dto.*;
import com.gulbagomedovich.brs.service.BusReservationService;
import com.gulbagomedovich.brs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/busreservation")
public class BusReservationController {

    private final UserService userService;

    private final BusReservationService busReservationService;

    @GetMapping("/stops")
    public Set<StopDto> getAllStops() {
        return busReservationService.getAllStops();
    }

    @GetMapping("/tripsbystops")
    public List<TripDto> getTripsbystops(TripScheduleDto tripScheduleDto) {
        return busReservationService.getTripsBetweenStops(
                tripScheduleDto.getInitialStop(), tripScheduleDto.getDestinationStop());
    }

    @GetMapping("/tripschedules")
    public List<TripScheduleDto> getTripSchedules(TripScheduleDto tripScheduleDto) {
        return busReservationService.getTripSchedules(
                tripScheduleDto.getInitialStop(),
                tripScheduleDto.getDestinationStop(),
                tripScheduleDto.getTripDate());
    }

    @PostMapping("/bookticket")
    public TicketDto bookTicket(TripScheduleDto tripScheduleDto, Principal principal) {
        UserDto userDto = userService.findUserByEmail(principal.getName());

        if (userDto != null) {
            TripDto tripDto = busReservationService.getTripById(tripScheduleDto.getTripId());

            if (tripDto != null) {
                tripScheduleDto = busReservationService.getTripSchedule(
                        tripDto, tripScheduleDto.getTripDate(), true);

                if (tripScheduleDto != null) {
                    TicketDto ticketDto = busReservationService.bookTicket(
                            tripScheduleDto, userDto);

                    if (ticketDto != null) {
                        return ticketDto;
                    }
                }
            }
        }

        return new TicketDto();
    }

}
