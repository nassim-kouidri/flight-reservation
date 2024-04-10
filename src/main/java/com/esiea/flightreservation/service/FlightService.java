package com.esiea.flightreservation.service;

import com.esiea.flightreservation.model.Flight;
import com.esiea.flightreservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class FlightService {

    private final FlightRepository flightRepository;

    public Flight getFlightById(UUID id) {
        return flightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Flight not found"));
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void updateFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public void deleteFlight(UUID id) {
        if(!flightRepository.existsById(id)) {

            log.error("Flight not found");
            throw new IllegalArgumentException("Flight not found");
        }
        flightRepository.deleteById(id);
    }

}
