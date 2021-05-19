package com.gulbagomedovich.brs.service;

import com.gulbagomedovich.brs.dto.*;
import com.gulbagomedovich.brs.model.*;
import com.gulbagomedovich.brs.repository.*;
import com.gulbagomedovich.brs.util.RandomStringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BusReservationService {

    private final UserRepository userRepository;

    private final BusRepository busRepository;

    private final TicketRepository ticketRepository;

    private final AgencyRepository agencyRepository;

    private final TripRepository tripRepository;

    private final TripScheduleRepository tripScheduleRepository;

    private final StopRepository stopRepository;

    public Set<StopDto> getAllStops() {
        Set<StopDto> stops = new TreeSet<>();

        for (Stop stop : stopRepository.findAll()) {
            stops.add(StopDto.toStopDto(stop));
        }

        return stops;
    }

    public StopDto getStopByCode(String code) {
        Stop stop = stopRepository.findByCode(code);

        if (stop != null) {
            return StopDto.toStopDto(stop);
        } else {
            return null;
        }
    }

    public AgencyDto getAgencyByOwner(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null) {
            Agency agency = agencyRepository.findByOwner(user);

            if (agency != null) {
                return AgencyDto.toAgencyDto(agency);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public AgencyDto addAgency(AgencyDto agencyDto) {
        User user = userRepository.findByEmail(agencyDto.getOwner().getEmail());

        if (user != null) {
            Agency agency = agencyRepository.findByName(agencyDto.getName());

            if (agency == null) {
                agency = new Agency()
                        .setCode(RandomStringUtil.getAlphanumericString(8))
                        .setName(agencyDto.getName())
                        .setDetails(agencyDto.getDetails())
                        .setOwner(user);

                return AgencyDto.toAgencyDto(agencyRepository.save(agency));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public AgencyDto updateAgency(AgencyDto agencyDto) {
        Agency agency = agencyRepository.findByName(agencyDto.getName());

        if (agency != null) {
            agency
                    .setName(agencyDto.getName())
                    .setDetails(agency.getDetails());

            return AgencyDto.toAgencyDto(agencyRepository.save(agency));
        } else {
            return null;
        }
    }

    @Transactional
    public BusDto addBus(AgencyDto agencyDto, BusDto busDto) {
        Agency agency = agencyRepository.findByName(agencyDto.getName());

        if (agency != null) {
            Bus bus = busRepository.findByCode(busDto.getCode());

            if (bus == null) {
                bus = new Bus()
                        .setCode(busDto.getCode())
                        .setModel(busDto.getModel())
                        .setCapacity(busDto.getCapacity())
                        .setAgency(agency);

                agency.getBuses().add(busRepository.save(bus));
                agencyRepository.save(agency);

                return BusDto.toBusDto(bus);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public TripDto getTripById(Long id) {
        Optional<Trip> trip = tripRepository.findById(id);

        if (trip.isPresent()) {
            return TripDto.toTripDto(trip.get());
        }

        return null;
    }

    @Transactional
    public List<TripDto> addTrip(TripDto tripDto) {
        Stop initialStop = stopRepository.findByCode(tripDto.getInitialStopCode());

        if (initialStop != null) {
            Stop destinationStop = stopRepository.findByCode(tripDto.getDestinationStopCode());

            if (destinationStop != null) {
                if (!initialStop.getCode().equals(destinationStop.getCode())) {
                    Agency agency = agencyRepository.findByCode(tripDto.getAgencyCode());

                    if (agency != null) {
                        Bus bus = busRepository.findByCode(tripDto.getBusCode());

                        if (bus != null) {
                            List<TripDto> trips = new ArrayList<>();

                            Trip trip = new Trip()
                                    .setFare(tripDto.getFare())
                                    .setJourneyTime(tripDto.getJourneyTime())
                                    .setBus(bus)
                                    .setAgency(agency)
                                    .setInitialStop(initialStop)
                                    .setDestinationStop(destinationStop);

                            Trip returnTrip = new Trip()
                                    .setFare(tripDto.getFare())
                                    .setJourneyTime(tripDto.getJourneyTime())
                                    .setBus(bus)
                                    .setAgency(agency)
                                    .setInitialStop(destinationStop)
                                    .setDestinationStop(initialStop);

                            trips.add(TripDto.toTripDto(tripRepository.save(trip)));
                            trips.add(TripDto.toTripDto(tripRepository.save(returnTrip)));

                            return trips;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public List<TripDto> getAgencyTrips(TripDto tripDto) {
        Agency agency = agencyRepository.findByCode(tripDto.getAgencyCode());

        if (agency != null) {
            List<TripDto> agencyTrips = new LinkedList<>();

            for (Trip trip : tripRepository.findAllByAgency(agency)) {
                agencyTrips.add(TripDto.toTripDto(trip));
            }

            return agencyTrips;
        } else {
            return null;
        }
    }

    public List<TripDto> getTripsBetweenStops(String initialStopCode, String destinationStopCode) {
        List<TripDto> tripsBetweenStops = new LinkedList<>();

        for (Trip trip : findTripsBetweenStops(initialStopCode, destinationStopCode)) {
            tripsBetweenStops.add(TripDto.toTripDto(trip));
        }

        return tripsBetweenStops;
    }

    public TripScheduleDto getTripSchedule(TripDto tripDto, Date tripDate, boolean createScheduleForTrip) {
        Optional<Trip> trip = tripRepository.findById(tripDto.getId());

        if (trip.isPresent()) {
            TripSchedule tripSchedule = tripScheduleRepository.findByTripAndTripDate(trip.get(), tripDate);

            if (tripSchedule != null) {
                return TripScheduleDto.toTripScheduleDto(tripSchedule);
            } else {
                if (createScheduleForTrip) {
                    tripSchedule = new TripSchedule()
                            .setAvailableSeats(trip.get().getBus().getCapacity())
                            .setTripDate(tripDate)
                            .setTrip(trip.get());

                    return TripScheduleDto.toTripScheduleDto(tripScheduleRepository.save(tripSchedule));
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public List<TripScheduleDto> getTripSchedules(String initialStopCode, String destinationStopCode,
                                               Date tripDate) {
        List<TripScheduleDto> tripSchedules = new LinkedList<>();

        for (Trip trip : findTripsBetweenStops(initialStopCode, destinationStopCode)) {
            tripSchedules.add(getTripSchedule(TripDto.toTripDto(trip), tripDate, true));
        }

        return tripSchedules;
    }

    @Transactional
    public TicketDto bookTicket(TripScheduleDto tripScheduleDto, UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        if (user != null) {
            Optional<TripSchedule> tripSchedule = tripScheduleRepository.findById(tripScheduleDto.getId());

            if (tripSchedule.isPresent()) {
                Ticket ticket = new Ticket()
                        .setSeatNumber(tripSchedule.get()
                                .getTrip().getBus().getCapacity() - tripSchedule.get().getAvailableSeats())
                        .setCancellable(false)
                        .setJourneyDate(tripSchedule.get().getTripDate())
                        .setPassenger(user);

                ticketRepository.save(ticket);
                tripSchedule.get().setAvailableSeats(tripSchedule.get().getAvailableSeats() - 1);
                tripScheduleRepository.save(tripSchedule.get());

                return TicketDto.toTicketDto(ticket);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private List<Trip> findTripsBetweenStops(String initialStopCode, String destinationStopCode) {
        Stop initialStop = stopRepository.findByCode(initialStopCode);

        if (initialStop != null) {
            Stop destinationStop = stopRepository.findByCode(destinationStopCode);

            if (destinationStop != null) {
                return tripRepository.findAllByInitialStopAndDestinationStop(initialStop, destinationStop);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
