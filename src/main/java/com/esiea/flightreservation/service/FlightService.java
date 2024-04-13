package com.esiea.flightreservation.service;

import com.esiea.flightreservation.model.Flight;
import com.esiea.flightreservation.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(UUID id) {
        return flightRepository.findById(id).orElseThrow(() ->  new IllegalArgumentException(String.format("Flight with id '%s' not found", id)));
    }

    @Transactional
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Transactional
    public Flight updateFlight(Flight flightRequest, UUID id) {
        Flight flight = getFlightById(id);
        flight.setAirportArrival(flightRequest.getAirportArrival());
        flight.setAirportDepart(flightRequest.getAirportDepart());
        flight.setArrivalDate(flightRequest.getArrivalDate());
        flight.setDepartDate(flightRequest.getDepartDate());
        flight.setDestination(flightRequest.getDestination());
        flight.setPlane(flightRequest.getPlane());
        flight.setPlaceNumber(flightRequest.getPlaceNumber());

        return flightRepository.save(flight);
    }

    @Transactional
    public void deleteFlight(UUID id) {
        if (!flightRepository.existsById(id)) {
            log.error(String.format("Flight with id '%s' not found", id));
            throw  new IllegalArgumentException(String.format("Flight with id '%s' not found", id));
        }
        flightRepository.deleteById(id);
    }

    public List<Flight> searchFlights(String destination, Date departureDate, Date arrivalDate) {
        return flightRepository.findByDestinationAndDepartDateGreaterThanEqualAndArrivalDateLessThanEqual(destination, departureDate, arrivalDate);
    }

}
