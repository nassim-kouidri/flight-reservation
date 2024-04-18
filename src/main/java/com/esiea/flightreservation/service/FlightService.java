package com.esiea.flightreservation.service;

import com.esiea.flightreservation.dto.FlightRequest;
import com.esiea.flightreservation.dto.FlightSearchRequest;
import com.esiea.flightreservation.model.Airport;
import com.esiea.flightreservation.model.Client;
import com.esiea.flightreservation.model.Flight;
import com.esiea.flightreservation.model.Plane;
import com.esiea.flightreservation.repository.FlightRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final PlaneService planeService;
    private final ClientService clientService;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(UUID id) {
        return flightRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(String.format("Flight with id '%s' not found", id)));
    }

    public List<Flight> searchFlightsByDepartureAndDestination(FlightSearchRequest request) {
        return flightRepository.findByDepartureAndDestinationAndDepartDateBetween(
                request.departure(),
                request.destination(),
                request.departDate(),
                request.arrivalDate()
        );
    }

    @Transactional
    public Flight saveFlight(FlightRequest flightRequest) {
        Plane plane = planeService.getPlaneById(flightRequest.planeId());
        Airport airportDepart = airportService.getAirportById(flightRequest.airportDepartId());
        Airport airportArrival = airportService.getAirportById(flightRequest.airportArrivalId());
        Client client = clientService.getClientById(flightRequest.clientId());

        Flight flight = createOrUpdateFlight(new Flight(), flightRequest, client, plane, airportDepart, airportArrival);

        return flightRepository.save(flight);
    }

    @Transactional
    public Flight updateFlight(FlightRequest flightRequest, UUID id) {
        Plane plane = planeService.getPlaneById(flightRequest.planeId());
        Airport airportDepart = airportService.getAirportById(flightRequest.airportDepartId());
        Airport airportArrival = airportService.getAirportById(flightRequest.airportArrivalId());
        Client client = clientService.getClientById(flightRequest.clientId());

        Flight flight = getFlightById(id);

        return createOrUpdateFlight(flight, flightRequest, client, plane, airportDepart, airportArrival);
    }

    @Transactional
    public void deleteFlight(UUID id) {
        if (!flightRepository.existsById(id)) {
            log.error(String.format("Flight with id '%s' not found", id));
            throw new IllegalArgumentException(String.format("Flight with id '%s' not found", id));
        }
        flightRepository.deleteById(id);
    }

    private Flight createOrUpdateFlight(Flight flight, FlightRequest flightRequest, Client client,
                                        Plane plane, Airport airportDepart, Airport airportArrival) {
        flight.setDeparture(flightRequest.departure());
        flight.setDestination(flightRequest.destination());
        flight.setDepartDate(flightRequest.departDate());
        flight.setArrivalDate(flightRequest.arrivalDate());
        flight.setPlaceNumber(flightRequest.placeNumber());
        flight.setAirportDepart(airportDepart);
        flight.setAirportArrival(airportArrival);
        flight.setPlane(plane);
        flight.setClient(client);

        return flight;
    }

}
