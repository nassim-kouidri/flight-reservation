package com.esiea.flightreservation.controller;

import com.esiea.flightreservation.model.Airport;
import com.esiea.flightreservation.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/airport")
@Validated
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/all")
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }

    @GetMapping("/{id}")
    public Airport getAirportById(@PathVariable UUID id) {
        return airportService.getAirportById(id);
    }

    @PostMapping("/save")
    public Airport saveAirport(@RequestBody Airport airport) {
        return airportService.saveAirport(airport);
    }

    @PutMapping("/update/{id}")
    public Airport updateAirport(@RequestBody Airport airport, @PathVariable UUID id) {
        return airportService.updateAirport(airport, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAirport(@PathVariable UUID id) {
        airportService.deleteAirport(id);
    }
}
