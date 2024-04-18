package com.esiea.flightreservation;

import com.esiea.flightreservation.dto.FlightRequest;
import com.esiea.flightreservation.dto.FlightSearchRequest;
import com.esiea.flightreservation.model.Airport;
import com.esiea.flightreservation.model.Client;
import com.esiea.flightreservation.model.Flight;
import com.esiea.flightreservation.model.Plane;
import com.esiea.flightreservation.repository.FlightRepository;
import com.esiea.flightreservation.service.AirportService;
import com.esiea.flightreservation.service.ClientService;
import com.esiea.flightreservation.service.FlightService;
import com.esiea.flightreservation.service.PlaneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private AirportService airportService;

    @Mock
    private PlaneService planeService;

    @Mock
    private ClientService clientService;

    @InjectMocks
    private FlightService flightService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveFlight() {
        FlightRequest flightRequest = getFlightRequest();

        Flight simulatedFlight = new Flight();
        when(planeService.getPlaneById(any(UUID.class))).thenReturn(new Plane());
        when(airportService.getAirportById(any(UUID.class))).thenReturn(new Airport());
        when(clientService.getClientById(any(UUID.class))).thenReturn(new Client());
        when(flightRepository.save(any(Flight.class))).thenReturn(simulatedFlight);

        Flight savedFlight = flightService.saveFlight(flightRequest);

        verify(flightRepository, times(1)).save(any(Flight.class));
        Assertions.assertEquals(simulatedFlight, savedFlight);
    }


    @Test
    public void testSearchFlightsByDepartureAndDestination() {
        FlightSearchRequest searchRequest = new FlightSearchRequest("New York", "Paris", new Date(), new Date());

        List<Flight> simulatedFlights = Arrays.asList(new Flight(), new Flight(), new Flight());
        when(flightRepository.findByDepartureAndDestinationAndDepartDateAndArrivalDate(anyString(), anyString(), any(Date.class), any(Date.class))).thenReturn(simulatedFlights);

        List<Flight> foundFlights = flightService.searchFlightsByDepartureAndDestination(searchRequest);

        verify(flightRepository, times(1)).findByDepartureAndDestinationAndDepartDateAndArrivalDate("New York", "Paris", searchRequest.departDate(), searchRequest.arrivalDate());
        Assertions.assertEquals(simulatedFlights, foundFlights);
    }

    private static FlightRequest getFlightRequest() {
        return new FlightRequest(
                "New York",
                "Paris",
                new Date(),
                new Date(),
                100,
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID());
    }
}
