package com.esiea.flightreservation.controller;

import com.esiea.flightreservation.model.Flight;
import com.esiea.flightreservation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
@Validated
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/all")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Flight getFlightById(@PathVariable UUID id) {
        return flightService.getFlightById(id);
    }

    @PostMapping("/save")
    public Flight saveFlight(@RequestBody Flight flight) {
        return flightService.saveFlight(flight);
    }

    @PutMapping("/update/{id}")
    public Flight updateFlight(@RequestBody Flight flight, @PathVariable UUID id) {
        return flightService.updateFlight(flight, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable UUID id) {
        flightService.deleteFlight(id);
    }
}